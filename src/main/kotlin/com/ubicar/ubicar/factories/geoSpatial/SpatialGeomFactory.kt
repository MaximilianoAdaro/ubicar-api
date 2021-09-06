package com.ubicar.ubicar.factories.geoSpatial

import com.vividsolutions.jts.geom.Coordinate
import com.vividsolutions.jts.geom.GeometryFactory
import com.vividsolutions.jts.geom.Point
import com.vividsolutions.jts.geom.Polygon
import com.vividsolutions.jts.geom.PrecisionModel
import org.springframework.stereotype.Component

@Component
class PointFactory {
  companion object {
    private val factory: GeometryFactory = GeometryFactory(PrecisionModel(), 4326)
    fun createPoint(x: Double, y: Double): Point {
      return factory.createPoint(Coordinate(x, y))
    }
  }
}

@Component
class PolygonFactory {
  companion object {
    private val factory: GeometryFactory = GeometryFactory(PrecisionModel(), 4326)
    fun createPolygon(points: List<Point>): Polygon {
      val coordinates: Array<Coordinate> = points.map { it.coordinate }.toTypedArray()
      return factory.createPolygon(coordinates)
    }
  }
}
