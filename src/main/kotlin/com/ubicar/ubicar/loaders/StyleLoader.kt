package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.repositories.SecurityRepository
import com.ubicar.ubicar.repositories.StyleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class StyleLoader(private val styleRepository: StyleRepository): CommandLineRunner, Ordered {

    override fun run(vararg args: String?) {
        val styles: MutableList<Style> = mutableListOf()
        styles.add(Style(44, "Contemporaneo"))
        styles.add(Style(45, "Mediterraneo"))
        styles.add(Style(46, "Minimalista"))
        styles.add(Style(47, "Colonial"))
        styles.add(Style(48, "Cottages"))
        styles.add(Style(49, "Tudor"))

        styles.map { styleRepository.save(it) }
    }

    override fun getOrder(): Int {
        return 3
    }
}
