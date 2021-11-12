package com.ubicar.ubicar.controllers.publicEndpoints

import com.ubicar.ubicar.dtos.PropertyDTO
import com.ubicar.ubicar.dtos.PropertyPreviewDTO
import com.ubicar.ubicar.dtos.UserContactDto
import com.ubicar.ubicar.dtos.ViewBoxCoordinatesDTOFloat
import com.ubicar.ubicar.dtos.filter.PropertyFilterDto
import com.ubicar.ubicar.dtos.filter.PropertyLazyTableDto
import com.ubicar.ubicar.dtos.filter.PropertySort
import com.ubicar.ubicar.factories.filter.FilterFactory
import com.ubicar.ubicar.factories.property.PropertyFactory
import com.ubicar.ubicar.factories.property.PropertyPreviewFactory
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.services.filter.FilterService
import com.ubicar.ubicar.services.geoSpatialService.GeoSpatialService
import com.ubicar.ubicar.services.predictor.PredictorService
import com.ubicar.ubicar.services.property.CsvPropertyService
import com.ubicar.ubicar.services.property.PropertyService
import com.ubicar.ubicar.services.user.RecentlyViewedService
import com.ubicar.ubicar.services.user.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/public/property")
class PropertyPublicController(
  private val propertyService: PropertyService,
  private val propertyFactory: PropertyFactory,
  private val propertyPreviewFactory: PropertyPreviewFactory,
  private val filterService: FilterService,
  private val filterFactory: FilterFactory,
  private val recentlyViewedService: RecentlyViewedService,
  private val userService: UserService,
  private val csvPropertyService: CsvPropertyService,
  private val propertyRepository: PropertyRepository,
  private val geoSpatialService: GeoSpatialService
) {

  @GetMapping("/preview")
  fun getProperties(@RequestParam page: Int): Page<PropertyPreviewDTO> {
    return propertyService.findAll(PageRequest.of(page, 16)).map { propertyPreviewFactory.convert(it) }
  }

  @PostMapping("/viewBox")
  fun getPropertiesViewBox(
    @RequestBody filter: PropertyFilterDto,
    @RequestParam b1: Double,
    @RequestParam b2: Double,
    @RequestParam b3: Double,
    @RequestParam b4: Double
  ): List<String> {
    val viewBoxCoordinatesDTOFloat = ViewBoxCoordinatesDTOFloat(b1, b2, b3, b4)
    return propertyService.findAllInViewBoxFiltered(filter, viewBoxCoordinatesDTOFloat)
  }

  @PostMapping("/preview/by-filter")
  fun getPropertiesFiltered(
    @RequestBody filter: PropertyFilterDto,
    @RequestParam b1: Double,
    @RequestParam b2: Double,
    @RequestParam b3: Double,
    @RequestParam b4: Double,
    @RequestParam(value = "page", required = false) page: Optional<Int>,
    @RequestParam(value = "size", required = false) size: Optional<Int>,
    @RequestParam(value = "direction", required = false) direction: Optional<Sort.Direction>,
    @RequestParam(value = "property", required = false) property: Optional<PropertySort>,
  ): Page<PropertyPreviewDTO> {
    if (SecurityContextHolder.getContext().authentication.principal != "anonymousUser") filterService.save(filterFactory.from(filter))
    val propertyLazyTableDto = PropertyLazyTableDto(
      page.orElse(0),
      size.orElse(16),
      direction.orElse(Sort.Direction.ASC),
      property.orElse(PropertySort.CREATION_DATE)
    )
    val viewBoxCoordinatesDTOFloat = ViewBoxCoordinatesDTOFloat(b1, b2, b3, b4)
    return propertyService.getAllByFilterPageable(filter, propertyLazyTableDto, viewBoxCoordinatesDTOFloat)
      .map { propertyPreviewFactory.convert(it) }
  }

  @GetMapping("/{id}")
  fun getProperty(@PathVariable id: String): PropertyDTO {
    val property = propertyService.findById(id)
    if (SecurityContextHolder.getContext().authentication.principal != "anonymousUser")
      recentlyViewedService.addRecentlyViewed(property, userService.findLogged())
    return propertyFactory.convert(property)
  }

  @PostMapping("/contact/{id}")
  fun contactPropertyOwner(@PathVariable id: String, @RequestBody contactDto: UserContactDto) {
    propertyService.contactOwner(id, contactDto)
  }

  @PostMapping("/csv/{propertyId}")
  fun createCsvFromProperty(@PathVariable propertyId: String) {
    csvPropertyService.createCsvFromProperty(propertyId)
  }

  @GetMapping("/set-geodata")
  fun setGeoData() {
    val list = propertyService.findAll()
    for (element in list) {
      val geo = geoSpatialService.storeGeodataOfProperty(element)
      element.geoData = geo
      propertyRepository.save(element)
    }
  }
}
