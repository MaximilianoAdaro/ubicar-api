package com.ubicar.ubicar.entities

enum class Condition {
  SALE,
  RENT
}

enum class TypeOfProperty {
  Casa,
  Quinta,
  Galpon,
  Cochera,
  Departamento,
  Terreno,
  Hotel,
  Compartido,
  PH,
  Local,
  Edificio,
  Loft,
  Oficina,
  Consultorio,
  Country,
  Flat
}

enum class Tags(val value: String) {
  Location("Ubicación"),
  Price("Precio"),
  Amenities("Comodidades"),
  SquareFeet("Metros Cuadrados"),
  Security("Seguridad"),
  Year("Año de construcción"),
  Material("Material de construcción");

  fun getValue(tag: Tags): String {
    return tag.value
  }
}
