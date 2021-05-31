package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.repositories.property.StyleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class StyleLoader(private val styleRepository: StyleRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val styles: MutableList<Style> = mutableListOf()
        styles.add(Style("Contemporaneo"))
        styles.add(Style("Mediterraneo"))
        styles.add(Style("Minimalista"))
        styles.add(Style("Colonial"))
        styles.add(Style("Cottages"))
        styles.add(Style("Tudor"))

        styles.map { styleRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 3
    }
}
