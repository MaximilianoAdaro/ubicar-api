package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.entities.Tags
import org.springframework.stereotype.Component

@Component
class TagFactory {

  fun convert(input: String): String {
    return Tags.valueOf(input).value
  }

  fun deconvert(input: String): String {
    return Tags.values().filter { it.value == input }[0].name
  }
}
