package com.ubicar.ubicar.factories.image

import com.ubicar.ubicar.entities.Image
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class ImageFactory {

  fun convert(image: MultipartFile): Image {
    return Image(
      image.originalFilename,
      image.contentType,
      image.bytes
    )

  }
}
