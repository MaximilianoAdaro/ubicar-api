package com.ubicar.ubicar.utils

import javax.mail.Session

class MailSender {

  fun setProperties(): Session? {
    val props = System.getProperties()
    props["mail.smtp.host"] = "smtp.gmail.com"
    props["mail.smtp.user"] = "bookin.team.austral"
    props["mail.smtp.clave"] = "bookin123"
    props["mail.smtp.auth"] = "true"
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.smtp.port"] = "587"
    return Session.getDefaultInstance(props)
  }
}
