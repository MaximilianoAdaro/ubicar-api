package com.ubicar.ubicar.loaders.loadersDto

import com.ubicar.ubicar.factories.geoSpatial.PointFactory
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Callable
import java.util.concurrent.ForkJoinPool
import java.util.stream.Collectors

class AbstractMethod {
  companion object {
    fun getContentFromPath(path: String): String {
      javaClass.getResourceAsStream(path).use { inputStream ->
        BufferedReader(InputStreamReader(inputStream!!)).use { reader ->
          return reader.lines().collect(Collectors.joining(System.lineSeparator()))
        }
      }
    }

    var list = listOf(
      "dRailway",
      "dIndustrialArea",
      "dAirport",
      "dEducation",
      "dFireStation",
      "dHealthBuilding",
      "dPenitentiary",
      "dPort",
      "dSecureBuilding",
      "dTrainStation",
      "dUniversity"
    )

    fun parallelStream(
      lines: MutableList<String>,
      geoSpatialService: GeoSpatialService
    ): MutableList<String> {
      var counter = 0
      val parallelism = 10
      var forkJoinPool: ForkJoinPool? = null
      val auxList: MutableList<String> = mutableListOf()
      System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10")
      try {
        forkJoinPool = ForkJoinPool(parallelism)
        forkJoinPool.submit(
          Callable {
            lines.parallelStream().forEach { line: String ->
              try {
                val split: List<String> = line.split(',')
                var aux: String = line
                val lat = split[1].toDouble()
                val long = split[2].toDouble()
                val runGeoDataUpdate = geoSpatialService.runGeoDataUpdate(PointFactory.createPoint(long, lat))
                runGeoDataUpdate.forEach { data -> aux += ",$data" }
                auxList.add(aux)
                println(counter++)
              } catch (e: Exception) {
                println("Exception: ${e.message}")
              }
            }
          }
        ).get()
      } catch (e: Exception) {
        println("Exception: ${e.message}")
      } finally {
        forkJoinPool?.shutdown()
      }
      return auxList
    }
  }
}
