package com.ubicar.ubicar.dtos.filter

val PROPERTY_SORT_PROPERTIES: Map<PropertySort, List<String>> = hashMapOf(
    PropertySort.ID to listOf("id"),
    PropertySort.PRICE to listOf("price"),
    PropertySort.CREATION_DATE to listOf("creationDate")
)