package com.ubicar.ubicar.services.image

import com.ubicar.ubicar.entities.Image
import com.ubicar.ubicar.repositories.image.ImageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImageService(
  val imageRepository: ImageRepository
) {

  fun save(image: Image) = imageRepository.save(image)

  fun saveAll(images: List<Image>): Iterable<Image> = imageRepository.saveAll(images)

  fun findById(id: String): Optional<Image> = imageRepository.findById(id)


}
