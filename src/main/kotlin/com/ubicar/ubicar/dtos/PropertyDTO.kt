package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.entities.Amenity
import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.ConstructionMaterial
import com.ubicar.ubicar.entities.Contact
import com.ubicar.ubicar.entities.OpenHouseDate
import com.ubicar.ubicar.entities.SecurityMeasure
import com.ubicar.ubicar.entities.Style
import com.ubicar.ubicar.entities.TypeOfProperty
import java.time.LocalDate
import java.time.LocalTime

data class CreatePropertyDTO(
  val title: String,
  val price: Int,
  val condition: String,
  val type: String,
  val address: AddressDTO,
  val squareFoot: Int,
  val coveredSquareFoot: Int,
  val levels: Int,
  val constructionDate: Int,
  val style: String,
  val environments: Int,
  val rooms: Int,
  val toilets: Int,
  val fullBaths: Int,
  val expenses: Int,
  val amenities: MutableList<String>,
  val materials: MutableList<String>,
  val security: MutableList<String>,
  val parkDescription: String,
  val links: MutableList<String>,
  val contacts: MutableList<ContactDto>,
  val openHouse: MutableList<OpenHouseDateDto>,
  val comments: String
)

data class AddressDTO(
  val town_id: String,
  val postalCode: String,
  val street: String,
  val number: Int,
  val department: String
)

data class PropertyDTO(
  val id: String,
  val title: String,
  val price: Int,
  val condition: Condition,
  val type: TypeOfProperty,
  val address: Address,
  val squareFoot: Int,
  val coveredSquareFoot: Int,
  val levels: Int,
  val constructionDate: Int,
  val style: Style,
  val environments: Int,
  val rooms: Int,
  val toilets: Int,
  val fullBaths: Int,
  val expenses: Int,
  val amenities: MutableList<Amenity>,
  val materials: MutableList<ConstructionMaterial>,
  val security: MutableList<SecurityMeasure>,
  val parkDescription: String,
  val links: MutableList<String>,
  val contacts: MutableList<Contact>,
  val openHouse: MutableList<OpenHouseDate>,
  val comments: String
)

data class PropertyPreviewDTO(
  val id: String,
  val title: String,
  val price: Int,
  val condition: Condition,
  val type: TypeOfProperty,
  val address: Address,
  val squareFoot: Int,
  val coveredSquareFoot: Int,
  val rooms: Int,
  val toilets: Int,
  val fullBaths: Int
)

data class ContactDto(
  var label: String,
  var email: String
)

data class OpenHouseDateDto(
  var day: LocalDate,
  var initialTime: LocalTime,
  var finalTime: LocalTime
)
