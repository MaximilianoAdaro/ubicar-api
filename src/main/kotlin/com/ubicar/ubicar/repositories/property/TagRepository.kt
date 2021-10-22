package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Tag
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface TagRepository : CrudRepository<Tag, String> {
  fun findFirstByValue(value: String): Optional<Tag>
}
