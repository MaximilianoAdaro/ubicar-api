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
import javax.persistence.criteria.*

@Repository
class PropertyFilterOperationRepository @Autowired constructor(
  private val em: EntityManager,
  private val amenityRepository: AmenityRepository,
) {
  fun getFilteredPropertiesPaginated(
    filter: PropertyFilterDto,
    pageable: Pageable,
    params: PropertyLazyTableDto,
    orderList: List<String>,
    polygon: Polygon
  ): Page<Property> {
    val cb = em.criteriaBuilder
    val cq: CriteriaQuery<Property> = cb.createQuery(Property::class.java)
    val root: Root<Property> = cq.from(Property::class.java)

    criteriaBuilderFilter(cb, root, filter, polygon, cq)

    val query: TypedQuery<Property> = em.createQuery(cq)

    val size = query.resultList.size
    val queryTasks: List<Property> = query.setFirstResult(pageable.offset.toInt())
      .setMaxResults(pageable.pageSize).resultList

    return PageImpl(queryTasks, pageable, size.toLong())
  }

  fun getFilteredProperties(filter: PropertyFilterDto, polygon: Polygon?): List<Property> {
    val cb = em.criteriaBuilder
    val cq: CriteriaQuery<Property> = cb.createQuery(Property::class.java)
    val root: Root<Property> = cq.from(Property::class.java)

    criteriaBuilderFilter(cb, root, filter, polygon, cq)

    val query: TypedQuery<Property> = em.createQuery(cq)

    return query.resultList
  }

  private fun criteriaBuilderFilter(
    cb: CriteriaBuilder,
    root: Root<Property>,
    filter: PropertyFilterDto,
    polygon: Polygon?,
    cq: CriteriaQuery<Property>
  ) {
    val predicates: MutableList<Predicate> = mutableListOf()

    predicates.add(cb.equal(root.get<Int>("step"), 7))

    if (filter.condition != null)
      predicates.add(cb.equal(root.get<Condition>("condition"), filter.condition))

    if (filter.typeProperty != null)
      predicates.add(cb.equal(root.get<TypeOfProperty>("type"), filter.typeProperty))

    if (filter.style != null)
      predicates.add(cb.equal(root.get<Style>("style").get<String>("id"), filter.style!!))

    if (filter.minPrice != null)
      predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.minPrice!!))

    if (filter.maxPrice != null)
      predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice!!))

    if (filter.minAmountRoom != null)
      predicates.add(cb.greaterThanOrEqualTo(root.get("rooms"), filter.minAmountRoom!!))

    if (filter.maxAmountRoom != null)
      predicates.add(cb.lessThanOrEqualTo(root.get("rooms"), filter.maxAmountRoom!!))

    if (filter.minAmountBathroom != null)
      predicates.add(cb.greaterThanOrEqualTo(root.get("fullBaths"), filter.minAmountBathroom!!))

    if (filter.maxAmountBathroom != null)
      predicates.add(cb.lessThanOrEqualTo(root.get("fullBaths"), filter.maxAmountBathroom!!))

    if (filter.minAmountSquareMeter != null)
      predicates.add(cb.greaterThanOrEqualTo(root.get("squareFoot"), filter.minAmountSquareMeter!!))

    if (filter.maxAmountSquareMeter != null)
      predicates.add(cb.lessThanOrEqualTo(root.get("squareFoot"), filter.maxAmountSquareMeter!!))

    val yardAmenity: Optional<Amenity> = amenityRepository.findFirstByLabel("Jardín")
    if (filter.containsYard != null && filter.containsYard!! && yardAmenity.isPresent) {
      val propertyAmenityJoin: Join<Property, Amenity> = root.join("amenities", JoinType.INNER)
      predicates.add(cb.equal(propertyAmenityJoin, yardAmenity.get()))
    }

    // DISTANCIAS A GEODATA
    val propertyGeoDataJoin: Join<Property, GeoDataProperty> = root.join("geoData", JoinType.INNER)

    if (filter.minDistanceSchool != null) {
      predicates.add(cb.greaterThanOrEqualTo(propertyGeoDataJoin.get("dEducation"), filter.minDistanceSchool!!))
    }

    if (filter.maxDistanceSchool != null) {
      predicates.add(cb.lessThanOrEqualTo(propertyGeoDataJoin.get("dEducation"), filter.maxDistanceSchool!!))
    }

    if (filter.minDistanceUniversity != null) {
      predicates.add(cb.greaterThanOrEqualTo(propertyGeoDataJoin.get("dUniversity"), filter.minDistanceUniversity!!))
    }

    if (filter.maxDistanceUniversity != null) {
      predicates.add(cb.lessThanOrEqualTo(propertyGeoDataJoin.get("dUniversity"), filter.maxDistanceUniversity!!))
    }

    if (filter.minDistanceFireStation != null) {
      predicates.add(cb.greaterThanOrEqualTo(propertyGeoDataJoin.get("dFireStation"), filter.minDistanceFireStation!!))
    }

    if (filter.maxDistanceFireStation != null) {
      predicates.add(cb.lessThanOrEqualTo(propertyGeoDataJoin.get("dFireStation"), filter.maxDistanceFireStation!!))
    }

    if (filter.minDistanceHospital != null) {
      predicates.add(cb.greaterThanOrEqualTo(propertyGeoDataJoin.get("dHealthBuilding"), filter.minDistanceHospital!!))
    }

    if (filter.maxDistanceHospital != null) {
      predicates.add(cb.lessThanOrEqualTo(propertyGeoDataJoin.get("dHealthBuilding"), filter.maxDistanceHospital!!))
    }

    if (filter.minDistancePenitentiary != null) {
      predicates.add(cb.greaterThanOrEqualTo(propertyGeoDataJoin.get("dPenitentiary"), filter.minDistancePenitentiary!!))
    }

    if (filter.maxDistanceCommissary != null) {
      predicates.add(cb.lessThanOrEqualTo(propertyGeoDataJoin.get("dSecureBuilding"), filter.maxDistanceCommissary!!))
    }

    if (filter.minDistanceSubway != null) {
      predicates.add(cb.greaterThanOrEqualTo(propertyGeoDataJoin.get("dSubway"), filter.minDistanceSubway!!))
    }

    if (filter.maxDistanceSubway != null) {
      predicates.add(cb.lessThanOrEqualTo(propertyGeoDataJoin.get("dSubway"), filter.maxDistanceSubway!!))
    }

    if (polygon != null) {
      val coords: Path<Geometry> = root.get<Address>("address").get("coordinates")!!
      val containsPredicate = ContainsPredicate(cb as CriteriaBuilderImpl, polygon, coords)
      predicates.add(containsPredicate)
    }

    //        Sorting and orders
    //        val orders: List<Order> = ArrayList()

    cq.where(*predicates.toTypedArray())
    //            .orderBy(orders)
  }

  fun checkFilters(property: Property, filters: List<Filter>): Filter? {
    var best = 0
    var filterSelected: Filter? = null
    for (filter in filters) {
      var actual = 0
      if (filter.condition != null && filter.condition!!.name == property.condition.name)
        actual += 1
      if (filter.typeProperty != null && filter.typeProperty!!.name == property.type.name)
        actual += 1
      if (filter.style != null && filter.style!!.label == property.style!!.label)
        actual += 1
      if (filter.minPrice != null && filter.minPrice!! >= property.price)
        actual += 1
      if (filter.maxPrice != null && filter.maxPrice!! <= property.price)
        actual += 1
      if (filter.minAmountRoom != null && filter.minAmountRoom!! >= property.rooms!!)
        actual += 1
      if (filter.maxAmountRoom != null && filter.maxAmountRoom!! <= property.rooms!!)
        actual += 1
      if (filter.minAmountBathroom != null && filter.minAmountBathroom!! >= (property.fullBaths!! + property.toilets!!))
        actual += 1
      if (filter.maxAmountBathroom != null && filter.maxAmountBathroom!! <= (property.fullBaths!! + property.toilets!!))
        actual += 1
      if (filter.minAmountSquareMeter != null && filter.minAmountSquareMeter!! >= property.squareFoot!!)
        actual += 1
      if (filter.maxAmountSquareMeter != null && filter.maxAmountSquareMeter!! <= property.squareFoot!!)
        actual += 1

      if (actual > best) {
        best = actual
        filterSelected = filter
      }
    }
    return filterSelected
  }
}
