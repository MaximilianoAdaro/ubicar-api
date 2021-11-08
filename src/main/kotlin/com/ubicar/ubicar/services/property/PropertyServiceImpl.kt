package com.ubicar.ubicar.services.property

import com.ubicar.ubicar.dtos.UserContactDto
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTO
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.dtos.filter.PROPERTY_SORT_PROPERTIES
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.entities.Image
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.factories.geoSpatial.PolygonFactory
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.services.address.AddressService
import com.ubicar.ubicar.services.contact.ContactService
import com.ubicar.ubicar.services.image.ImageService
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
  private val propertyFilterService: PropertyFilterService,
  private val velocityEngine: VelocityEngine,
  private val sessionUtils: SessionUtils,
  private val imageService: ImageService,
  private val csvPropertyService: CsvPropertyService,
  private val tagsLikedService: TagsLikedService,
  private val userService: UserService
) : PropertyService {

  override fun findAll(pageable: Pageable): Page<Property> {
    return propertyRepository.findAll(pageable)
  }

  override fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTO): List<Property> {
    val createPolygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTO.toPointList())
    return propertyRepository.findAllInViewBoxProperty(createPolygon)
  }

  override fun findAllInViewBoxProp(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat): List<Property> {
    val createPolygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTO.toDto().toPointList())
    return propertyRepository.findAllInViewBoxProperty(createPolygon)
  }

  override fun findAllInViewBoxFiltered(
    filter: PropertyFilterDto,
    viewBoxCoordinatesDTOFloat: ViewBoxCoordinatesDTOFloat
  ): List<String> {
    val polygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTOFloat.toDto().toPointList())
    return propertyFilterService.filterPropertiesViewBox(filter, polygon)
  }

  override fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat): List<String> {
    val createPolygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTO.toDto().toPointList())
    return propertyRepository.findAllInViewBox(createPolygon)
  }

  override fun save(property: Property, images: List<Image>): Property {
    val savedImages = imageService.saveAll(images)
    property.images = savedImages.toMutableList()
    if (property.step > 1) addressService.save(property.address!!)
    property.contacts.map { contactService.save(it) }
    property.openHouse.map { openHouseDateService.save(it) }
    val propertySaved = propertyRepository.save(property)
    //createCsvProperty(propertySaved)
    return propertySaved
  }

  override fun createCsvProperty(property: Property) {
    if (property.step == 7)
      csvPropertyService.createCsvFromProperty(property)
  }

  override fun findById(id: String): Property {
    return propertyRepository.findById(id).orElseThrow { NotFoundException("Property not found") }
  }

  override fun update(id: String, property: Property, images: List<Image>, imagesToDelete: List<String>): Property {
    val propertySaved = propertyRepository
      .findById(id)
      .map { old ->

        val savedImages = imageService.saveAll(images)

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
        old.step = property.step

        val imageList = old.images
          .filterNot { image -> imagesToDelete.contains(image.id) }
          .plus(savedImages)
          .toMutableList()
        old.images = imageList

        propertyRepository.save(old)
      }
      .orElseThrow { NotFoundException("Property not found") }
    createCsvProperty(propertySaved)
    return propertySaved
  }

  override fun delete(id: String) = propertyRepository.delete(findById(id))

  override fun like(id: String): Property {
    val property: Property = findById(id)
    val logged = sessionUtils.getTokenUserInformation()
    if (property.likes.contains(logged)) throw BadRequestException("You already liked this property")
    property.likes.add(logged)
    val newProperty = propertyRepository.save(property)
    if (tagsLikedService.findByPropertyIdAndUserId(newProperty.id, logged.id) == null)
      tagsLikedService.create(newProperty, logged)
    return newProperty
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
    params: PropertyLazyTableDto,
    viewBoxCoordinatesDTOFloat: ViewBoxCoordinatesDTOFloat
  ): Page<Property> {
    val orderList = PROPERTY_SORT_PROPERTIES[params.property]!!
    val pageRequest = PageRequest.of(params.page, params.size)
    val polygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTOFloat.toDto().toPointList())
    return propertyFilterService.filterPropertiesPaginated(filter, pageRequest, params, orderList, polygon)
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

  override fun mostLiked(): List<Property> {
    val properties = propertyRepository.findAll()
    properties.sortByDescending { it.likes.size }
    return if (properties.size > 10) properties.subList(0, 10)
    else properties
  }

  private fun opportunityMail(property: Property) {
    val session: Session? = setProperties()
    val list = userService.getInversores()
    for (user in list) {
      sendMailOpportunity(
        velocityEngine,
        session,
        "Encontramos una oportunidad que podría interesarte",
        "opportunity.html",
        property,
        user.email
      )
    }
  }

  override fun getOpportunities(): List<Property> {
    return propertyRepository.getAllOpportunities()
  }

  override fun isOpportunity(id: String): Property {
    val property = findById(id)
    property.isOpportunity = true
    opportunityMail(property)
    return propertyRepository.save(property)
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

  private fun setConfigurations(velocityEngine: VelocityEngine, message: MimeMessage, session: Session?, template: String?, velocityContext: VelocityContext) {
    val stringWriter = StringWriter()
    velocityEngine.mergeTemplate(template, "UTF-8", velocityContext, stringWriter)
    message.setContent(stringWriter.toString(), "text/html; charset=utf-8")
    val transport = session?.getTransport("smtp")
    transport?.connect("smtp.gmail.com", "ubicar.austral2021", "Lab3Ubicar2021")
    transport?.sendMessage(message, message.allRecipients)
    transport?.close()
  }

  fun sendMailOpportunity(
    velocityEngine: VelocityEngine,
    session: Session?,
    subject: String?,
    template: String?,
    property: Property,
    recipient: String
  ) {
    val message = MimeMessage(session)
    try {
      message.setFrom(InternetAddress("ubicar.austral2021"))
      message.addRecipient(Message.RecipientType.TO, InternetAddress(recipient))
      message.subject = subject
      val velocityContext = VelocityContext()
      val condition = if(property.condition.name == "SALE") "En Venta" else "En Alquiler"
      velocityContext.put("id", property.id)
      velocityContext.put("title", property.title)
      velocityContext.put("department", property.address?.department)
      velocityContext.put("number", property.address?.number)
      velocityContext.put("street", property.address?.street?.toLowerCase()?.capitalize())
      velocityContext.put("state", property.address?.city?.state?.name?.toLowerCase()?.capitalize())
      velocityContext.put("city", property.address?.city?.name?.toLowerCase()?.capitalize())
      velocityContext.put("rooms", property.rooms)
      velocityContext.put("baths", property.fullBaths)
      velocityContext.put("squared", property.coveredSquareFoot)
      velocityContext.put("condition", condition)
      velocityContext.put("price", property.price)
      setConfigurations(velocityEngine, message, session, template, velocityContext)
    } catch (me: MessagingException) {
      me.printStackTrace()
    }
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
      setConfigurations(velocityEngine, message, session, template, velocityContext)
    } catch (me: MessagingException) {
      me.printStackTrace()
    }
  }
}
