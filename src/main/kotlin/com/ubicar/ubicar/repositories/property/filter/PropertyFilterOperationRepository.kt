package com.ubicar.ubicar.repositories.property.filter

import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.*
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.filter.predicate.ContainsPredicate
import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.geom.Polygon
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.Optional
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class PropertyFilterOperationRepository @Autowired constructor(
  private val em: EntityManager,
  private val amenityRepository: AmenityRepository,
) {
  fun getFilteredProperties(
    filter: PropertyFilterDto,
    pageable: Pageable,
    params: PropertyLazyTableDto,
    orderList: List<String>,
    polygon: Polygon
  ): Page<Property> {
    val cb = em.criteriaBuilder
    val cq: CriteriaQuery<Property> = cb.createQuery(Property::class.java)
    val root: Root<Property> = cq.from(Property::class.java)

    val predicates: MutableList<Predicate> = mutableListOf()

    predicates.add(cb.equal(root.get<Int>("step"), 7))

    if (filter.condition != null) {
      predicates.add(cb.equal(root.get<Condition>("condition"), filter.condition))
    }

    if (filter.typeProperty != null) {
      predicates.add(cb.equal(root.get<TypeOfProperty>("type"), filter.typeProperty))
    }

    if (filter.style != null) {
      predicates.add(cb.equal(root.get<Style>("style").get<String>("id"), filter.style!!))
    }

    if (filter.minPrice != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.minPrice!!))
    }
    if (filter.maxPrice != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice!!))
    }

    if (filter.minAmountRoom != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("rooms"), filter.minAmountRoom!!))
    }
    if (filter.maxAmountRoom != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("rooms"), filter.maxAmountRoom!!))
    }

    if (filter.minAmountBathroom != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("fullBaths"), filter.minAmountBathroom!!))
    }
    if (filter.maxAmountBathroom != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("fullBaths"), filter.maxAmountBathroom!!))
    }

    if (filter.minAmountSquareMeter != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("squareFoot"), filter.minAmountSquareMeter!!))
    }
    if (filter.maxAmountSquareMeter != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("squareFoot"), filter.maxAmountSquareMeter!!))
    }

    val yardAmenity: Optional<Amenity> = amenityRepository.findFirstByLabel("Jardín")
    if (filter.containsYard != null && filter.containsYard!! && yardAmenity.isPresent) {
      val propertyAmenityJoin: Join<Property, Amenity> = root.join("amenities", JoinType.INNER)
      predicates.add(cb.equal(propertyAmenityJoin, yardAmenity.get()))
    }

    val coords: Path<Geometry> = root.get<Address>("address").get("coordinates")!!
    val containsPredicate = ContainsPredicate(cb as CriteriaBuilderImpl, polygon, coords)
    predicates.add(containsPredicate)

//        Sorting and orders
//        val orders: List<Order> = ArrayList()

    cq.where(*predicates.toTypedArray())
//            .orderBy(orders)
    val query: TypedQuery<Property> = em.createQuery(cq)

    val size = query.resultList.size
    val queryTasks: List<Property> = query.setFirstResult(pageable.offset.toInt())
      .setMaxResults(pageable.pageSize).resultList

    return PageImpl(queryTasks, pageable, size.toLong())
  }
}
