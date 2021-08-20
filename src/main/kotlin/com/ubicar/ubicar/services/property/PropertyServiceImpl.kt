package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.UserContactDto
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTO
import com.ubicar.ubicar.dtos.filter.PROPERTY_SORT_PROPERTIES
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.factories.geoSpatial.PolygonFactory
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.services.address.AddressService
import com.ubicar.ubicar.services.contact.ContactService
import com.ubicar.ubicar.services.openHouseDate.OpenHouseDateService
import com.ubicar.ubicar.services.user.UserService
import com.ubicar.ubicar.utils.BadRequestException
import com.ubicar.ubicar.utils.NotFoundException
import com.ubicar.ubicar.utils.SessionUtils
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.io.StringWriter
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class PropertyServiceImpl(
  private val propertyRepository: PropertyRepository,
  private val addressService: AddressService,
  private val contactService: ContactService,
  private val openHouseDateService: OpenHouseDateService,
  private val userService: UserService,
  private val propertyFilterService: PropertyFilterService,
  private val velocityEngine: VelocityEngine,
  private val sessionUtils: SessionUtils
) : PropertyService {

  override fun findAll(pageable: Pageable): Page<Property> {
    return propertyRepository.findAll(pageable)
  }

  override fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTO): List<Property> {
    val createPolygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTO.toPointList())
    return propertyRepository.findAllInViewBox(createPolygon)
  }

  override fun save(property: Property): Property {
    addressService.save(property.address)
    property.contacts.map { contactService.save(it) }
    property.openHouse.map { openHouseDateService.save(it) }
    return propertyRepository.save(property)
  }

  override fun findById(id: String): Property {
    return propertyRepository.findById(id).orElseThrow { NotFoundException("Property not found") }
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
      .orElseThrow { NotFoundException("Property not found") }
  }

  override fun delete(id: String) = propertyRepository.delete(findById(id))

  override fun like(id: String): Property {
    val property: Property = findById(id)
    val logged = sessionUtils.getTokenUserInformation()
    if (property.likes.contains(logged)) throw BadRequestException("You already liked this property")
    property.likes.add(logged)
    return propertyRepository.save(property)
  }

  override fun dislike(id: String): Property {
    val property: Property = findById(id)
    val logged = sessionUtils.getTokenUserInformation()
    if (!property.likes.contains(logged)) throw BadRequestException("You never liked this property")
    property.likes.remove(logged)
    return propertyRepository.save(property)
  }

  override fun getAllByFilterPageable(
    filter: PropertyFilterDto,
    params: PropertyLazyTableDto
  ): Page<Property> {
    val orderList = PROPERTY_SORT_PROPERTIES[params.property]!!
    val pageRequest = PageRequest.of(params.page, params.size)
    return propertyFilterService.filterEvaluationsPaginated(filter, pageRequest, params, orderList)
  }

  override fun getAllFavoritePropertiesByUser(user: User): List<Property> {
    return user.likedProperties
  }

  override fun getAllPropertiesOfUser(user: User): List<Property> {
    return propertyRepository.findByOwnerId(user.id)
  }

  override fun contactOwner(id: String, contactDto: UserContactDto) {
    val property: Property = findById(id)
    val owner: User = property.owner
    val session: Session? = setProperties()
    var html = "contact.html"
    if (contactDto.cellphone == "" || contactDto.cellphone == " ") {
      html = "contactnophone.html"
    }
    sendMail(
      velocityEngine,
      session,
      owner,
      "Consulta sobre su propiedad: " + property.title,
      html,
      contactDto
    )
  }

  fun setProperties(): Session? {
    val props = System.getProperties()
    props["mail.smtp.host"] = "smtp.gmail.com"
    props["mail.smtp.user"] = "ubicar.austral2021"
    props["mail.smtp.clave"] = "Lab3Ubicar2021"
    props["mail.smtp.auth"] = "true"
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.smtp.port"] = "587"
    return Session.getDefaultInstance(props)
  }

  fun sendMail(
    velocityEngine: VelocityEngine,
    session: Session?,
    owner: User,
    subject: String?,
    template: String?,
    contactDTO: UserContactDto
  ) {
    val message = MimeMessage(session)
    try {
      message.setFrom(InternetAddress("ubicar.austral2021"))
      message.addRecipient(Message.RecipientType.TO, InternetAddress(owner.email))
      message.subject = subject
      val velocityContext = VelocityContext()
      velocityContext.put("name", contactDTO.name)
      velocityContext.put("email", contactDTO.email)
      velocityContext.put("cellphone", contactDTO.cellphone)
      velocityContext.put("message", contactDTO.message)
      val stringWriter = StringWriter()
      velocityEngine.mergeTemplate(template, "UTF-8", velocityContext, stringWriter)
      message.setContent(stringWriter.toString(), "text/html; charset=utf-8")
      val transport = session?.getTransport("smtp")
      transport?.connect("smtp.gmail.com", "ubicar.austral2021", "Lab3Ubicar2021")
      transport?.sendMessage(message, message.allRecipients)
      transport?.close()
    } catch (me: MessagingException) {
      me.printStackTrace()
    }
  }
}
