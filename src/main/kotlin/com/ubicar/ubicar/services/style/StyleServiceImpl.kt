package com.ubicar.ubicar.services.style

import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.repositories.StyleRepository
import org.springframework.stereotype.Service

@Service
class StyleServiceImpl(private val repository: StyleRepository): StyleService {
    override fun findAll(): List<Style> {
        return repository.findAll()
    }

    override fun save(style: Style): Style {
        return repository.save(style)
    }

    override fun findById(id: Long): Style {
        return repository.findById(id).orElseThrow()
    }

    override fun delete(style: Long) {
        repository.delete(findById(style))
    }
}
