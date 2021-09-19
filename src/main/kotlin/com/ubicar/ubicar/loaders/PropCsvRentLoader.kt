package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.loaders.loadersDto.AbstractMethod
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import java.io.File

@Profile("!test")
@Component
class PropCsvRentLoader(
  private val geoSpatialService: GeoSpatialService
) :
  CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val amountOfFiles = 9
    for (i in 1 until amountOfFiles) {
      val filePath = "rent_part_$i"
      val lines: MutableList<String> =
        File("/home/maxi/projects/ubicar/ubicar-api/src/main/resources/propData/$filePath.csv").readLines()
          .toMutableList()
      AbstractMethod.list.forEach { lines[0] += ",$it" }
      val auxList: MutableList<String> = AbstractMethod.parallelStream(lines, geoSpatialService)
      println("lines.size: ${lines.size}")
      println("Done")
      val text = auxList.joinToString("\n")
      File("/home/maxi/projects/ubicar/ubicar-api/src/main/resources/propData/$filePath-output.csv").writeText(text) // write to new file
    }
  }

  override fun getOrder(): Int {
    return 10
  }
}
