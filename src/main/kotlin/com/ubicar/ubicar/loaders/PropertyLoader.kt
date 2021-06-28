package com.ubicar.ubicar.loaders

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.entities.City
import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.entities.Contact
import com.ubicar.ubicar.entities.Coordinates
import com.ubicar.ubicar.entities.Country
import com.ubicar.ubicar.entities.OpenHouseDate
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.entities.State
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.entities.TypeOfProperty
import com.ubicar.ubicar.repositories.location.CityRepository
import com.ubicar.ubicar.repositories.location.CountryRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import com.ubicar.ubicar.repositories.property.AmenityRepository
import com.ubicar.ubicar.repositories.property.MaterialRepository
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
  private val countryRepository: CountryRepository,
  private val stateRepository: StateRepository,
  private val cityRepository: CityRepository,
  private val userRepository: UserRepository
) : CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    val properties: MutableList<Property> = mutableListOf()

    val country = countryRepository.save(Country("Argentina"))
    val state = stateRepository.save(State("Buenos Aires", country))
    val owner = userRepository.findByEmail("admin@admin.com").get()

    // Property 1
    val city1 = cityRepository.save(City("San Isidro", state))
    val coordinates1 = Coordinates(-34.4892169630285, -58.564152054237304)
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
      "Casa a muy buen precio en el mejor lugar",
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
      "Es un parque muy bonito",
      mutableListOf(),
      mutableListOf(Contact("Agente", "agente@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "Comentarios adicionales de lo linda que es esta propiedad",
      LocalDate.now(),
      mutableListOf(),
      owner
    )
    properties.add(property1)

    // Property 2 ---------------------------------------------------------------------
    val city2 = cityRepository.save(City("General Rodríguez", state))
    val coordinates2 = Coordinates(-34.608284019555796, -58.9601312273656)

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
      "Depto muy top en la mejor zona de Rodriguez",
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
      mutableListOf(Contact("Vendedor", "dueña_de_depto_top@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "Todos tus amigos van a querer venir a visitarte!",
      LocalDate.now(),
      mutableListOf(),
      owner
    )
    properties.add(property2)

    // Property 3 ---------------------------------------------------------------------
    val city3 = cityRepository.save(City("Vicente López", state))
    val coordinates3 = Coordinates(-34.52748821083418, -58.47600318016971)

    val address3 = Address(city3, "carlos f melo", 124, coordinates3)
    val style3: Style = styleRepository.findFirstByLabel("Colonial").get()
    val amenities3: MutableList<Amenity> = mutableListOf(
      amenitiesRepository.findFirstByLabel("Cochera").get(),
      amenitiesRepository.findFirstByLabel("Patio").get(),
      amenitiesRepository.findFirstByLabel("Baulera").get(),
      amenitiesRepository.findFirstByLabel("Terraza").get(),
      amenitiesRepository.findFirstByLabel("Jardín").get(),
    )
    val materials3: MutableList<ConstructionMaterial> =
      mutableListOf(
        materialRepository.findFirstByLabel("Chapa").get(),
      )
    val securities3: MutableList<SecurityMeasure> = mutableListOf(
      securityRepository.findFirstByLabel("Rejas").get(),
      securityRepository.findFirstByLabel("Alarma de entrada").get(),
    )
    val property3 = Property(
      "Casa top en Vicente López",
      405000000,
      Condition.SALE,
      TypeOfProperty.Casa,
      address3,
      150,
      130,
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
      "No tiene un espacio muy grande",
      mutableListOf(),
      mutableListOf(Contact("Vendedor", "dueña_de_depto@gmail.com")),
      mutableListOf(OpenHouseDate(LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
      "Todos tus amigos van a querer venir a visitarte!",
      LocalDate.now(),
      mutableListOf(),
      owner
    )
    properties.add(property3)

    properties.map { propertyService.save(it) }
  }

  override fun getOrder(): Int {
    return 9
  }
}
