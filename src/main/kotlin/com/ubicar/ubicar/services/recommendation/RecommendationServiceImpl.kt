package com.ubicar.ubicar.services.recommendation

import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.Recommendation
import com.ubicar.ubicar.repositories.recommendation.RecommendationRepository
import com.ubicar.ubicar.services.user.UserService
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.stereotype.Service
import java.io.StringWriter
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class RecommendationServiceImpl(
  private val recommendationRepository: RecommendationRepository,
  private val userService: UserService,
  private val velocityEngine: VelocityEngine,
  ) : RecommendationService {

  override fun save(recommendation: Recommendation): Recommendation {
    return recommendationRepository.save(recommendation)
  }

  override fun findByFilter(id: String): List<Recommendation> {
    return recommendationRepository.findByFilter(id)
  }

  override fun getRecommendations(size: Int): List<Recommendation> {
    val list = recommendationRepository.findByUser(userService.findLogged().id).asReversed()
    return if(list.size >= size) list.subList(0,size) else list
  }

  override fun recommendationsMail(recommendation: Recommendation) {
    val session: Session? = setProperties()
    val properties = recommendation.properties
    val html = if (properties.size >= 3) "recommendation.html" else if (properties.size == 2) "recommendation2.html" else "recommendation1.html"
    sendMailRecommendations(
      velocityEngine,
      session,
      "Tenemos algunas recomendaciones para vos en base a tus busquedas",
      html,
      properties,
      userService.findLogged().email
    )
  }

  override fun deleteByProperty(newProperty: Property) {
    val recommendation = recommendationRepository.findByProperty(newProperty.id)
    if (recommendation != null) {
      recommendation.properties.clear()
      recommendationRepository.delete(recommendationRepository.save(recommendation))
    }
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

  private fun setConfigurations(
    velocityEngine: VelocityEngine,
    message: MimeMessage,
    session: Session?,
    template: String?,
    velocityContext: VelocityContext
  ) {
    val stringWriter = StringWriter()
    velocityEngine.mergeTemplate(template, "UTF-8", velocityContext, stringWriter)
    message.setContent(stringWriter.toString(), "text/html; charset=utf-8")
    val transport = session?.getTransport("smtp")
    transport?.connect("smtp.gmail.com", "ubicar.austral2021", "Lab3Ubicar2021")
    transport?.sendMessage(message, message.allRecipients)
    transport?.close()
  }

  fun sendMailRecommendations(
    velocityEngine: VelocityEngine,
    session: Session?,
    subject: String?,
    template: String?,
    properties: List<Property>,
    recipient: String
  ) {
    val message = MimeMessage(session)
    try {
      message.setFrom(InternetAddress("ubicar.austral2021"))
      message.addRecipient(Message.RecipientType.TO, InternetAddress(recipient))
      message.subject = subject
      val velocityContext = VelocityContext()
      properties.forEachIndexed { index, property ->
        val condition = if (property.condition.name == "SALE") "En Venta" else "En Alquiler"
        velocityContext.put("id$index", property.id)
        velocityContext.put("title$index", property.title)
        velocityContext.put("department$index", property.address?.city?.department)
        velocityContext.put("number$index", property.address?.number)
        velocityContext.put("street$index", property.address?.street?.toLowerCase()?.capitalize())
        velocityContext.put("state$index", property.address?.city?.state?.name?.toLowerCase()?.capitalize())
        velocityContext.put("city$index", property.address?.city?.name?.toLowerCase()?.capitalize())
        velocityContext.put("rooms$index", property.rooms)
        velocityContext.put("baths$index", property.fullBaths)
        velocityContext.put("squared$index", property.coveredSquareFoot)
        velocityContext.put("condition$index", condition)
        velocityContext.put("price$index", property.price)
      }
      setConfigurations(velocityEngine, message, session, template, velocityContext)
    } catch (me: MessagingException) {
      me.printStackTrace()
    }
  }
}
