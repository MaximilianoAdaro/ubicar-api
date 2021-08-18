package com.ubicar.ubicar.entities

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Table(name = "image")
@Entity
class Image(
  val fileName: String?,
  val fileType: String?,
  @Lob val fileData: ByteArray,
) : AbstractEntity() {
}
