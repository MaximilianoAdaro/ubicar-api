package com.ubicar.ubicar.controllers.publicEndpoints.image

import com.ubicar.ubicar.services.image.ImageService
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("public/image")
class ImageController(
  val imageService: ImageService
) {

  @GetMapping("{id}")
  fun getImage(@PathVariable id: String): ResponseEntity<Resource> {
    val image = imageService.findById(id)
    if (image.isEmpty) return ResponseEntity.notFound().build()

    val fileName = image.get().fileName ?: image.get().id
    return when {
      image.get().fileType != null -> ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(image.get().fileType!!))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${fileName}")
        .body(ByteArrayResource(image.get().fileData))
      else -> ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${fileName}")
        .body(ByteArrayResource(image.get().fileData))
    }

  }
}
