package com.ubicar.ubicar.loaders.loadersDto

import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

@Component
class AbstractMethod {
  fun getContentFromPath(path: String): String {
    javaClass.getResourceAsStream(path).use { inputStream ->
      BufferedReader(InputStreamReader(inputStream!!)).use { reader ->
        return reader.lines().collect(Collectors.joining(System.lineSeparator()))
      }
    }
  }
}
