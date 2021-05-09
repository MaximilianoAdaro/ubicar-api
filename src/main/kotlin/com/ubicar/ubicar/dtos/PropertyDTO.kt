package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.*

data class CreatePropertyDTO(
    private var title: String,
    private var price: Int,
    private var condition: Condition,
    private var type: TypeOfProperty,
    private var address: Address,
    private var squareFoot: Int,
    private var levels: Int,
    private var constructionDate: Int,
    private var style: Style,
    private var rooms: Int,
    private var quarterBaths: Int,
    private var halfBaths: Int,
    private var threeQuarterBaths: Int,
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
    fun render() = Property(0, title, price, condition, type, address, squareFoot, levels, constructionDate, style, rooms, quarterBaths, halfBaths, threeQuarterBaths, fullBaths, expenses, amenities, materials, security, parkDescription, links, contacts, openHouse, comments)
}

data class PropertyDTO(
    private var id: Long,
    private var title: String,
    private var price: Int,
    private var condition: Condition,
    private var type: TypeOfProperty,
    private var address: Address,
    private var squareFoot: Int,
    private var levels: Int,
    private var constructionDate: Int,
    private var style: Style,
    private var rooms: Int,
    private var quarterBaths: Int,
    private var halfBaths: Int,
    private var threeQuarterBaths: Int,
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
)
