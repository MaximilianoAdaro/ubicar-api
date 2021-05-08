package com.ubicar.ubicar.dtos

import com.ubicar.ubicar.entities.Condition
import com.ubicar.ubicar.entities.Style

data class CreatePropertyDTO(
    var price: Int,
    var condition: Condition,
    var address: String,
    var squareFoot: Int,
    var constructionDate: Int,
    var style: Style,
    var rooms: Int,
    var quarterBaths: Int,
    var halfBaths: Int,
    var threeQuarterBaths: Int,
    var fullBaths: Int,
    var expenses: Int
)

data class PropertyDTO(
    val id: Long,
    var price: Int,
    var condition: Condition,
    var address: String,
    var squareFoot: Int,
    var constructionDate: Int,
    var style: Style,
    var rooms: Int,
    var quarterBaths: Int,
    var halfBaths: Int,
    var threeQuarterBaths: Int,
    var fullBaths: Int,
    var expenses: Int
)
