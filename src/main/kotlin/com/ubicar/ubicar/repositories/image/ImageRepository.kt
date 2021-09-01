package com.ubicar.ubicar.repositories.image

import com.ubicar.ubicar.entities.Image
import org.springframework.data.repository.CrudRepository

interface ImageRepository : CrudRepository<Image, String> {
}
