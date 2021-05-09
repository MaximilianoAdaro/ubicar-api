package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.Address
import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.Property
import com.ubicar.ubicar.entities.Style

data class CreatePropertyDTO(
    private var title: String,
    private var price: Int,
    private var condition: Condition,
    private var address: Address,
    private var squareFoot: Int,
    private var constructionDate: Int,
    private var style: Style,
    private var rooms: Int,
    private var quarterBaths: Int,
    private var halfBaths: Int,
    private var threeQuarterBaths: Int,
    private var fullBaths: Int,
    private var expenses: Int
) {
    fun render() = Property(0, title, price, condition, address, squareFoot, constructionDate, style, rooms, quarterBaths, halfBaths, threeQuarterBaths, fullBaths, expenses)
}

data class PropertyDTO(
    private var id: Long,
    private var title: String,
    private var price: Int,
    private var condition: Condition,
    private var address: Address,
    private var squareFoot: Int,
    private var constructionDate: Int,
    private var style: Style,
    private var rooms: Int,
    private var quarterBaths: Int,
    private var halfBaths: Int,
    private var threeQuarterBaths: Int,
    private var fullBaths: Int,
    private var expenses: Int
)
