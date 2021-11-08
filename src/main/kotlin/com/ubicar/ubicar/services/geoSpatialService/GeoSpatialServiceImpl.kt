package com.ubicar.ubicar.services.geoSpatialService

import com.ubicar.ubicar.dtos.GeoType
import com.ubicar.ubicar.dtos.GeoType.AIR
import com.ubicar.ubicar.dtos.GeoType.FIRE
import com.ubicar.ubicar.dtos.GeoType.HOSPITAL
import com.ubicar.ubicar.dtos.GeoType.IND
import com.ubicar.ubicar.dtos.GeoType.JAIL
import com.ubicar.ubicar.dtos.GeoType.POLICE
import com.ubicar.ubicar.dtos.GeoType.PORT
import com.ubicar.ubicar.dtos.GeoType.RAIL
import com.ubicar.ubicar.dtos.GeoType.SCHOOL
import com.ubicar.ubicar.dtos.GeoType.SUBWAY_STATION
import com.ubicar.ubicar.dtos.GeoType.TRAINSTATION
import com.ubicar.ubicar.dtos.GeoType.UNIV
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.entities.GeoDataProperty
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.factories.geoSpatial.PolygonFactory
import com.ubicar.ubicar.repositories.geoData.GeoDataPropertyRepository
import com.ubicar.ubicar.repositories.geoSpatial.AirportRepository
import com.ubicar.ubicar.repositories.geoSpatial.EducationalStablishmentRepository
import com.ubicar.ubicar.repositories.geoSpatial.FireStationRepository
import com.ubicar.ubicar.repositories.geoSpatial.HealthBuildingRepository
import com.ubicar.ubicar.repositories.geoSpatial.IndustrialZoneRepository
import com.ubicar.ubicar.repositories.geoSpatial.PenitenciaryRepository
import com.ubicar.ubicar.repositories.geoSpatial.PoliceRepository
import com.ubicar.ubicar.repositories.geoSpatial.PortRepository
import com.ubicar.ubicar.repositories.geoSpatial.RailwayRepository
import com.ubicar.ubicar.repositories.geoSpatial.SubwayRepository
import com.ubicar.ubicar.repositories.geoSpatial.TrainStationRepository
import com.ubicar.ubicar.repositories.geoSpatial.UniversityRepository
import com.ubicar.ubicar.utils.BadRequestException
import com.vividsolutions.jts.geom.Point
import org.springframework.stereotype.Service

@Service
class GeoSpatialServiceImpl(
  val universityRepository: UniversityRepository,
  val airportRepository: AirportRepository,
  val portRepository: PortRepository,
  val fireStationRepository: FireStationRepository,
  val educationalStablishmentRepository: EducationalStablishmentRepository,
  val railwayRepository: RailwayRepository,
  val healthBuildingRepository: HealthBuildingRepository,
  val policeRepository: PoliceRepository,
  val penitenciaryRepository: PenitenciaryRepository,
  val industrialZoneRepository: IndustrialZoneRepository,
  val trainStationRepository: TrainStationRepository,
  val subwayRepository: SubwayRepository,
  val geoDataPropertyRepository: GeoDataPropertyRepository
) : GeoSpatialService {

  override fun findAllInViewBox(viewBoxCoordinatesDTO: ViewBoxCoordinatesDTOFloat, geoType: GeoType): List<String> {
    val polygon = PolygonFactory.createPolygon(viewBoxCoordinatesDTO.toDto().toPointList())
    return when (geoType) {
      UNIV -> universityRepository.findAllInViewBox(polygon)
      // ROUTES -> propertyRepository.findAllInViewBox(createPolygon)
      AIR -> airportRepository.findAllInViewBox(polygon)
      PORT -> portRepository.findAllInViewBox(polygon)
      FIRE -> fireStationRepository.findAllInViewBox(polygon)
      SCHOOL -> educationalStablishmentRepository.findAllInViewBox(polygon)
      RAIL -> railwayRepository.findAllInViewBox(polygon)
      HOSPITAL -> healthBuildingRepository.findAllInViewBox(polygon)
      POLICE -> policeRepository.findAllInViewBox(polygon)
      JAIL -> penitenciaryRepository.findAllInViewBox(polygon)
      IND -> industrialZoneRepository.findAllInViewBox(polygon)
      TRAINSTATION -> trainStationRepository.findAllInViewBox(polygon)
      SUBWAY_STATION -> subwayRepository.findAllInViewBox(polygon)
      else -> throw BadRequestException("Not available ${geoType.name} geo type")
    }
  }

  override fun runAllUpdates() {
    val propertiesNotRun = geoDataPropertyRepository.findAllWherePropertyNotFound()
    propertiesNotRun.forEach(this::runGeoDataUpdate)
  }

  fun runGeoDataUpdate(property: Property) {
    val coordinates = property.address!!.coordinates
    val geoDataProperty = GeoDataProperty(
      property,
      railwayRepository.calculateMinDistanceFromCoords(coordinates),
      industrialZoneRepository.calculateMinDistanceFromCoords(coordinates),
      airportRepository.calculateMinDistanceFromCoords(coordinates),
      educationalStablishmentRepository.calculateMinDistanceFromCoords(coordinates),
      fireStationRepository.calculateMinDistanceFromCoords(coordinates),
      healthBuildingRepository.calculateMinDistanceFromCoords(coordinates),
      penitenciaryRepository.calculateMinDistanceFromCoords(coordinates),
      portRepository.calculateMinDistanceFromCoords(coordinates),
      policeRepository.calculateMinDistanceFromCoords(coordinates),
      trainStationRepository.calculateMinDistanceFromCoords(coordinates),
      universityRepository.calculateMinDistanceFromCoords(coordinates),
      subwayRepository.calculateMinDistanceFromCoords(coordinates)
    )

    geoDataPropertyRepository.save(geoDataProperty)
  }

  override fun runGeoDataUpdate(coordinates: Point): List<Double> {
    return listOf(
      railwayRepository.calculateMinDistanceFromCoords(coordinates),
      industrialZoneRepository.calculateMinDistanceFromCoords(coordinates),
      airportRepository.calculateMinDistanceFromCoords(coordinates),
      educationalStablishmentRepository.calculateMinDistanceFromCoords(coordinates),
      fireStationRepository.calculateMinDistanceFromCoords(coordinates),
      healthBuildingRepository.calculateMinDistanceFromCoords(coordinates),
      penitenciaryRepository.calculateMinDistanceFromCoords(coordinates),
      portRepository.calculateMinDistanceFromCoords(coordinates),
      policeRepository.calculateMinDistanceFromCoords(coordinates),
      trainStationRepository.calculateMinDistanceFromCoords(coordinates),
      universityRepository.calculateMinDistanceFromCoords(coordinates),
      subwayRepository.calculateMinDistanceFromCoords(coordinates)
    )
  }
}
