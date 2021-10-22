package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.entities.Contact
import com.ubicar.ubicar.entities.OpenHouseDate
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.entities.TypeOfProperty
import com.ubicar.ubicar.factories.geoSpatial.PointFactory
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.MaterialRepository
import com.ubicar.ubicar.repositories.property.PropertyRepository
import com.ubicar.ubicar.repositories.property.SecurityRepository
import com.ubicar.ubicar.repositories.property.StyleRepository
import com.ubicar.ubicar.repositories.user.UserRepository
import com.ubicar.ubicar.services.property.PropertyService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime

@Profile("!test")
@Component
class PropertyLoader(
  private val propertyService: PropertyService,
  private val styleRepository: StyleRepository,
  private val amenitiesRepository: AmenityRepository,
  private val materialRepository: MaterialRepository,
  private val securityRepository: SecurityRepository,
  private val stateRepository: StateRepository,
  private val cityRepository: CityRepository,
  private val userRepository: UserRepository,
  private val propertyRepository: PropertyRepository
) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    if (propertyRepository.totalAmount() > 1) return
    val properties: MutableList<Property> = mutableListOf()

    val state = stateRepository.findFirstByName("Buenos Aires").orElseThrow()
    val owner = userRepository.findByEmail("ubicar.austral2021@gmail.com").get()

    // Property 1
    val city1 = cityRepository.findByNameAndState("SAN ISIDRO", state).orElseThrow()
    val coordinates1 = PointFactory.createPoint(-58.564152054237304, -34.4892169630285)
    val address = Address(city1, "eliseo reclus", 1030, coordinates1)
    val style: Style = styleRepository.findFirstByLabel("Contemporaneo").get()
    val amenities: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Balcón").get(),
      amenitiesRepository.findFirstByLabel("Comedor").get(),
      amenitiesRepository.findFirstByLabel("Dormitorio en Suite").get(),
      amenitiesRepository.findFirstByLabel("Baulera").get(),
      amenitiesRepository.findFirstByLabel("Comedor Diario").get(),
    )
    val materials: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Ladrillo").get(),
        materialRepository.findFirstByLabel("Madera").get()
      )
    val securities: MutableList<SecurityMeasure> = mutableListOf(securityRepository.findFirstByLabel("Rejas").get())
    val property1 = Property(
      "Casa a muy buen precio en la zona más cara de Buenos Aires",
      5000000,
      Condition.SALE,
      TypeOfProperty.Casa,
      address,
      100,
      80,
      2,
      1980,
      style,
      3,
      2,
      2,
      1,
      2500,
      amenities,
      materials,
      securities,
      "Cuenta con una parrilla, pileta y un gran espacio libre",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueño@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "",
      LocalDate.now(),
      mutableListOf(),
      owner,
      7
    )
    properties.add(property1)

    // Property 2 ---------------------------------------------------------------------
    val city2 = cityRepository.findByNameAndState("GENERAL RODRIGUEZ", state).orElseThrow()
    val coordinates2 = PointFactory.createPoint(-58.9601312273656, -34.608284019555796)

    val address2 = Address(city2, "av eva peron", 350, coordinates2)
    val style2: Style = styleRepository.findFirstByLabel("Colonial").get()
    val amenities2: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Cochera").get(),
      amenitiesRepository.findFirstByLabel("Patio").get(),
      amenitiesRepository.findFirstByLabel("Dormitorio en Suite").get(),
      amenitiesRepository.findFirstByLabel("Baulera").get(),
      amenitiesRepository.findFirstByLabel("Terraza").get(),
      amenitiesRepository.findFirstByLabel("Jardín").get(),
    )
    val materials2: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Chapa").get(),
        materialRepository.findFirstByLabel("Piedra").get()
      )
    val securities2: MutableList<SecurityMeasure> = mutableListOf(
      securityRepository.findFirstByLabel("Rejas").get(),
      securityRepository.findFirstByLabel("Camaras").get(),
      securityRepository.findFirstByLabel("Alarma de entrada").get(),
    )
    val property2 = Property(
      "Departamento en la mejor zona de Rodriguez",
      25000000,
      Condition.SALE,
      TypeOfProperty.Departamento,
      address2,
      150,
      150,
      1,
      2010,
      style2,
      4,
      2,
      1,
      1,
      5000,
      amenities2,
      materials2,
      securities2,
      "No tiene",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueño@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "Tiene lugar suficiente para invitar a mucha gente",
      LocalDate.now(),
      mutableListOf(),
      owner,
      7
    )
    properties.add(property2)

    // Property 3 ---------------------------------------------------------------------
    val city3 = cityRepository.findByNameAndState("VICENTE LOPEZ", state).orElseThrow()
    val coordinates3 = PointFactory.createPoint(-58.47600318016971, -34.52748821083418)

    val address3 = Address(city3, "carlos f melo", 124, coordinates3)
    val style3: Style = styleRepository.findFirstByLabel("Contemporaneo").get()
    val amenities3: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Cochera").get(),
      amenitiesRepository.findFirstByLabel("Patio").get(),
      amenitiesRepository.findFirstByLabel("Baulera").get(),
      amenitiesRepository.findFirstByLabel("Jardín").get(),
    )
    val materials3: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Cemento").get(),
        materialRepository.findFirstByLabel("Ladrillo").get(),
      )
    val securities3: MutableList<SecurityMeasure> = mutableListOf(
      securityRepository.findFirstByLabel("Rejas").get(),
      securityRepository.findFirstByLabel("Alarma de entrada").get(),
    )
    val property3 = Property(
      "Casa modesta en Vicente López",
      995000000,
      Condition.SALE,
      TypeOfProperty.Casa,
      address3,
      50,
      45,
      1,
      2002,
      style3,
      4,
      3,
      2,
      2,
      10000,
      amenities3,
      materials3,
      securities3,
      "Tiene 5 metros cuadrados de pasto",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueño@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "",
      LocalDate.now(),
      mutableListOf(),
      owner,
      7
    )
    properties.add(property3)

    // Property 4 ---------------------------------------------------------------------
    val state4 = stateRepository.findFirstByName("Tucumán").orElseThrow()
    val city4 = cityRepository.findByNameAndState("LA TRINIDAD", state4).orElseThrow()
    val coordinates4 = PointFactory.createPoint(-65.5250428, -27.4157679)

    val address4 = Address(city4, "colon", 190, coordinates4)
    val style4: Style = styleRepository.findFirstByLabel("Contemporaneo").get()
    val amenities4: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Cochera").get(),
      amenitiesRepository.findFirstByLabel("Patio").get(),
      amenitiesRepository.findFirstByLabel("Baulera").get(),
      amenitiesRepository.findFirstByLabel("Terraza").get(),
      amenitiesRepository.findFirstByLabel("Jardín").get(),
    )
    val materials4: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Chapa").get(),
      )
    val securities4: MutableList<SecurityMeasure> = mutableListOf(
      securityRepository.findFirstByLabel("Rejas").get(),
      securityRepository.findFirstByLabel("Alarma de entrada").get(),
    )
    val property4 = Property(
      "Depto muy cómodo para alquiler en La Trinidad",
      20000,
      Condition.RENT,
      TypeOfProperty.Departamento,
      address4,
      80,
      80,
      1,
      1995,
      style4,
      4,
      3,
      2,
      2,
      1000,
      amenities4,
      materials4,
      securities4,
      "No tiene",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueño@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "",
      LocalDate.now(),
      mutableListOf(),
      owner,
      7
    )
    properties.add(property4)

    // Property 7 ---------------------------------------------------------------------
    val city7 = cityRepository.findByNameAndState("LANUS ESTE", state).orElseThrow()
    val coordinates7 = PointFactory.createPoint(-58.3812447, -34.712017)

    val address7 = Address(city7, "suipacha", 1130, coordinates7)
    val style7: Style = styleRepository.findFirstByLabel("Colonial").get()
    val amenities7: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Patio").get(),
      amenitiesRepository.findFirstByLabel("Jardín").get(),
    )
    val materials7: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Ladrillo Hueco").get(),
      )
    val securities7: MutableList<SecurityMeasure> = mutableListOf(
      securityRepository.findFirstByLabel("Rejas").get(),
    )
    val property7 = Property(
      "Terreno en venta en Lanús",
      1350000,
      Condition.SALE,
      TypeOfProperty.Terreno,
      address7,
      500,
      150,
      2,
      1995,
      style7,
      3,
      1,
      1,
      1,
      2000,
      amenities7,
      materials7,
      securities7,
      "Mucho parque con pasto bien cuidado",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueño@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "Perfecto para construir una casa",
      LocalDate.now(),
      mutableListOf(),
      owner,
      7
    )
    properties.add(property7)

    // Property 8 ---------------------------------------------------------------------
    val city8 = cityRepository.findByNameAndState("BAHIA BLANCA", state).orElseThrow()
    val coordinates8 = PointFactory.createPoint(-62.2799104, -38.7252996)

    val address8 = Address(city8, "brandsen", 439, coordinates8)
    val style8: Style = styleRepository.findFirstByLabel("Minimalista").get()
    val amenities8: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Cochera").get(),
      amenitiesRepository.findFirstByLabel("Patio").get(),
      amenitiesRepository.findFirstByLabel("Jardín").get(),
    )
    val materials8: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Ladrillo").get(),
        materialRepository.findFirstByLabel("Ladrillo Hueco").get(),
      )
    val securities8: MutableList<SecurityMeasure> = mutableListOf(
      securityRepository.findFirstByLabel("Rejas").get(),
      securityRepository.findFirstByLabel("Camaras").get(),
    )
    val property8 = Property(
      "Casa en venta en Bahia Blanca",
      3465000,
      Condition.SALE,
      TypeOfProperty.Casa,
      address8,
      200,
      160,
      1,
      2007,
      style8,
      7,
      4,
      1,
      2,
      1000,
      amenities8,
      materials8,
      securities8,
      "Tiene una pileta muy linda para refrescarse y un área con mucho pasto sano",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueño@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "",
      LocalDate.now(),
      mutableListOf(),
      owner,
      7
    )
    properties.add(property8)

    properties.map { propertyService.save(it, listOf()) }
  }

  override fun getOrder(): Int {
    return 10
  }
}
