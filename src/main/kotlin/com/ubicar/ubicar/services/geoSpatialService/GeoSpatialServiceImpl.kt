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
import com.ubicar.ubicar.dtos.GeoType.TRAINSTATION
import com.ubicar.ubicar.dtos.GeoType.UNIV
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.factories.geoSpatial.PolygonFactory
import com.ubicar.ubicar.repositories.geoSpatial.AirportRepository
import com.ubicar.ubicar.repositories.geoSpatial.EducationalStablishmentRepository
import com.ubicar.ubicar.repositories.geoSpatial.FireStationRepository
import com.ubicar.ubicar.repositories.geoSpatial.HealthBuildingRepository
import com.ubicar.ubicar.repositories.geoSpatial.IndustrialZoneRepository
import com.ubicar.ubicar.repositories.geoSpatial.PenitenciaryRepository
import com.ubicar.ubicar.repositories.geoSpatial.PoliceRepository
import com.ubicar.ubicar.repositories.geoSpatial.PortRepository
import com.ubicar.ubicar.repositories.geoSpatial.RailwayRepository
import com.ubicar.ubicar.repositories.geoSpatial.TrainStationRepository
import com.ubicar.ubicar.repositories.geoSpatial.UniversityRepository
import com.ubicar.ubicar.utils.BadRequestException
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
  val trainStationRepository: TrainStationRepository
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
      else -> throw BadRequestException("Not available ${geoType.name} geo type")
    }
  }
}
