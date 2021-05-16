package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.*

data class CreatePropertyDTO(
    private var title: String,
    private var price: Int,
    private var condition: Condition,
    private var type: TypeOfProperty,
    private var address: Address,
    private var squareFoot: Int,
    private var coveredSquareFoot: Int,
    private var levels: Int,
    private var constructionDate: Int,
    private var style: Style,
    private var environments: Int,
    private var rooms: Int,
    private var toilettes: Int,
    private var fullBaths: Int,
    private var expenses: Int,
    private var amenities:MutableList<Amenity>,
    private var materials: MutableList<ConstructionMaterial>,
    private var security: MutableList<SecurityMeasure>,
    private var parkDescription: String,
    private var links: MutableList<String>,
    private var contacts: MutableList<Contact>,
    private var openHouse: MutableList<OpenHouseDate>,
    private var comments: String
) {
    fun render() = Property(0, title, price, condition, type, address, squareFoot, coveredSquareFoot, levels, constructionDate, style, environments, rooms, toilettes, fullBaths, expenses, amenities, materials, security, parkDescription, links, contacts, openHouse, comments)
}

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
    val toilettes: Int,
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
    val toilettes: Int,
    val fullBaths: Int
)
