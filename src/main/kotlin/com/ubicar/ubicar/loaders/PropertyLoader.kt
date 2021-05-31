package com.ubicar.ubicar.loaders

//import com.ubicar.ubicar.entities.*
//import com.ubicar.ubicar.repositories.*
//import com.ubicar.ubicar.repositories.location.TownRepository
//import com.ubicar.ubicar.repositories.property.AmenityRepository
//import com.ubicar.ubicar.repositories.property.MaterialRepository
//import com.ubicar.ubicar.repositories.property.SecurityRepository
//import com.ubicar.ubicar.repositories.property.StyleRepository
//import com.ubicar.ubicar.services.property.PropertyService
//import org.springframework.boot.CommandLineRunner
//import org.springframework.context.annotation.Profile
//import org.springframework.core.Ordered
//import org.springframework.stereotype.Component
//import java.time.LocalDate
//import java.time.LocalTime
//
//@Profile("!test")
//@Component
//class PropertyLoader(private val propertyService: PropertyService,
//                     private val townRepository: TownRepository,
//                     private val styleRepository: StyleRepository,
//                     private val amenitiesRepository: AmenityRepository,
//                     private val materialRepository: MaterialRepository,
//                     private val securityRepository: SecurityRepository): CommandLineRunner, Ordered {
//
//    override fun run(vararg args: String?) {
//        val properties: MutableList<Property> = mutableListOf()
//
//        // Property 1
//        val address = Address(0, townRepository.findById(62).get(), "1426", "Av. Cabildo", 1030, "1")
//        val style: Style = styleRepository.findById(46).get()
//        val amenities: MutableList<Amenity> = mutableListOf(amenitiesRepository.findById(1).get(), amenitiesRepository.findById(4).get(), amenitiesRepository.findById(9).get(), amenitiesRepository.findById(15).get(), amenitiesRepository.findById(19).get(), amenitiesRepository.findById(20).get(), amenitiesRepository.findById(25).get())
//        val materials: MutableList<ConstructionMaterial> = mutableListOf(materialRepository.findById(35).get(), materialRepository.findById(36).get())
//        val securities: MutableList<SecurityMeasure> = mutableListOf(securityRepository.findById(41).get())
//        properties.add(Property(0, "Casa a muy buen precio en el mejor lugar", 5000000, Condition.SALE,
//        TypeOfProperty.Casa, address, 100, 80, 2, 1980, style,
//        3, 2, 2, 1, 2500, amenities, materials, securities,
//        "Es un parque muy bonito", mutableListOf("https://www.youtube.com/watch?v=yDcAwTH88qw&ab_channel=LaCimaTV"),
//        mutableListOf(Contact(0, "Agente", "agente@gmail.com")), mutableListOf(OpenHouseDate(0, LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
//        "Comentarios adicionales de lo linda que es esta propiedad"))
//
//        // Property 2
//        val address2 = Address(0, townRepository.findById(62).get(), "1425", "Av. Luis Maria Campos", 300, "3C")
//        val style2: Style = styleRepository.findById(49).get()
//        val amenities2: MutableList<Amenity> = mutableListOf(amenitiesRepository.findById(2).get(), amenitiesRepository.findById(8).get(), amenitiesRepository.findById(9).get(), amenitiesRepository.findById(13).get(), amenitiesRepository.findById(25).get(), amenitiesRepository.findById(26).get(), amenitiesRepository.findById(32).get())
//        val materials2: MutableList<ConstructionMaterial> = mutableListOf(materialRepository.findById(37).get(), materialRepository.findById(38).get())
//        val securities2: MutableList<SecurityMeasure> = mutableListOf(securityRepository.findById(40).get())
//        properties.add(Property(0, "Depto muy top en la mejor zona de Belgrano", 25000000, Condition.SALE,
//            TypeOfProperty.Departamento, address2, 150, 150, 1, 2010, style2,
//            4, 2, 1, 1, 5000, amenities2, materials2, securities2,
//            "No tiene", mutableListOf("https://www.youtube.com/watch?v=qh8Z3wHor2g&ab_channel=ConstruyeHogar"),
//            mutableListOf(Contact(0, "Vendedor", "dueña_de_depto_top@gmail.com")), mutableListOf(OpenHouseDate(0, LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT)),
//            "Todos tus amigos van a querer venir a visitarte!"))
//
//        properties.map { propertyService.save(it) }
//    }
//
//    override fun getOrder(): Int {
//        return 8
//    }
//}
