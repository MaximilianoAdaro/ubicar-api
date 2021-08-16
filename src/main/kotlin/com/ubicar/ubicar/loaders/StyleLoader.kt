package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.repositories.property.StyleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class StyleLoader(private val styleRepository: StyleRepository) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val styles: List<String> = mutableListOf(
      "Contemporaneo", "Mediterraneo", "Minimalista", "Colonial", "Cottages", "Tudor"
    )

    styles.forEach {
      styleRepository.findFirstByLabel(it).orElseGet {
        styleRepository.save(Style(it))
      }
    }
  }

  override fun getOrder(): Int {
    return 3
  }
}
