package com.ubicar.ubicar.entities

import org.hibernate.annotations.Type
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Table(name = "image")
@Entity
class Image(
  val fileName: String?,
  val fileType: String?,

  @Lob
  @Type(type = "org.hibernate.type.BinaryType")
  val fileData: ByteArray,
) : AbstractEntity() {
}
