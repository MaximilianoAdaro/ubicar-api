package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.filter.PROPERTY_SORT_PROPERTIES
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.services.address.AddressService
import com.ubicar.ubicar.services.contact.ContactService
import com.ubicar.ubicar.services.openHouseDate.OpenHouseDateService
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.utils.BadRequestException
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository,
    private val addressService: AddressService,
    private val contactService: ContactService,
    private val openHouseDateService: OpenHouseDateService,
    private val userService: UserService,
    private val propertyFilterService: PropertyFilterService
) : PropertyService {

    override fun findAll(pageable: Pageable): Page<Property> {
        return propertyRepository.findAll(pageable)
    }

    override fun save(property: Property): Property {
        addressService.save(property.address)
        property.contacts.map { contactService.save(it) }
        property.openHouse.map { openHouseDateService.save(it) }
        return propertyRepository.save(property)
    }

    override fun findById(id: String): Property {
        return propertyRepository.findById(id).orElseThrow()
    }

    override fun update(id: String, property: Property): Property {
        return propertyRepository
            .findById(id)
            .map { old ->

                // Hacerlo mas lindo
                old.javaClass.declaredFields


                old.title = property.title
                old.price = property.price
                old.condition = property.condition
                old.type = property.type
                old.address = property.address
                old.squareFoot = property.squareFoot
                old.coveredSquareFoot = property.coveredSquareFoot
                old.levels = property.levels
                old.constructionDate = property.constructionDate
                old.style = property.style
                old.environments = property.environments
                old.rooms = property.rooms
                old.toilets = property.toilets
                old.fullBaths = property.fullBaths
                old.expenses = property.expenses
                old.amenities = property.amenities
                old.materials = property.materials
                old.security = property.security
                old.parkDescription = property.parkDescription
                old.links = property.links
                old.contacts = property.contacts
                old.openHouse = property.openHouse
                old.comments = property.comments
                propertyRepository.save(old)
            }
            .orElseThrow { NotFoundException("User not found") }
    }

    override fun delete(id: String) = propertyRepository.delete(findById(id))

    override fun like(id: String): Property {
        val property: Property = findById(id)
        val logged: User = userService.findByEmail(SecurityContextHolder.getContext().authentication.name).get()
        property.likes.map { if (it.id == logged.id) throw BadRequestException("You already liked this property") }
        property.likes.add(logged)
        return propertyRepository.save(property)
    }

    override fun dislike(id: String): Property {
        val property: Property = findById(id)
        val logged: User = userService.findByEmail(SecurityContextHolder.getContext().authentication.name).get()
        property.likes.map { if (it.id != logged.id) throw BadRequestException("You never liked this property") }
        property.likes = property.likes.filter { it.id == logged.id }.toMutableList()
        return propertyRepository.save(property)
    }

    override fun getAllByFilterPageable(
        filter: PropertyFilterDto, params: PropertyLazyTableDto
    ): Page<Property> {
        val orderList = PROPERTY_SORT_PROPERTIES[params.property]!!
        val pageRequest = PageRequest.of(params.page, params.size)
        return propertyFilterService.filterEvaluationsPaginated(filter, pageRequest, params, orderList)
    }
}
