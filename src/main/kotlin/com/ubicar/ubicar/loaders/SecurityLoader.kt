package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.repositories.property.SecurityRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class SecurityLoader(private val securityRepository: SecurityRepository) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val securities: MutableList<SecurityMeasure> = mutableListOf()
    securities.add(SecurityMeasure("Rejas"))
    securities.add(SecurityMeasure("Camaras"))
    securities.add(SecurityMeasure("Alarma de entrada"))
    securities.add(SecurityMeasure("Alarma de humo"))

    securities.map { securityRepository.save(it) }
  }

  override fun getOrder(): Int {
    return 2
  }
}
