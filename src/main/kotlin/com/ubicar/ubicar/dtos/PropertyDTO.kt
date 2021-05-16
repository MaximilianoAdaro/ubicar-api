package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.*

data class CreatePropertyDTO(
    val title: String,
    val price: Int,
    val condition: String,
    val type: String,
    val address: Address,
    val squareFoot: Int,
    val coveredSquareFoot: Int,
    val levels: Int,
    val constructionDate: Int,
    val style: Long,
    val environments: Int,
    val rooms: Int,
    val toilets: Int,
    val fullBaths: Int,
    val expenses: Int,
    val amenities:MutableList<Long>,
    val materials: MutableList<Long>,
    val security: MutableList<Long>,
    val parkDescription: String,
    val links: MutableList<String>,
    val contacts: MutableList<Contact>,
    val openHouse: MutableList<OpenHouseDate>,
    val comments: String
)

data class PropertyDTO(
    val id: Long,
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
    val amenities:MutableList<Amenity>,
    val materials: MutableList<ConstructionMaterial>,
    val security: MutableList<SecurityMeasure>,
    val parkDescription: String,
    val links: MutableList<String>,
    val contacts: MutableList<Contact>,
    val openHouse: MutableList<OpenHouseDate>,
    val comments: String
)

data class PropertyPreviewDTO(
    val id: Long,
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
