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
    val securities: List<String> = mutableListOf(
      "Rejas", "Camaras", "Alarma de entrada", "Alarma de humo"
    )

    securities.forEach {
      securityRepository.findFirstByLabel(it)
        .orElseGet { securityRepository.save(SecurityMeasure(it)) }
    }
  }

  override fun getOrder(): Int {
    return 2
  }
}
