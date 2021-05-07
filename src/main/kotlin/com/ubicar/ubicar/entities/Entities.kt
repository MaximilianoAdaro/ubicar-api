package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*


class Entities {

    @Table(name = "property")
    @Entity
    class Property(

        @Column(name = "id")
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,

        // REQUIRED FEATURES

        @Column(nullable = false, name = "price")
        var price: Int,

        @Column(nullable = false, name = "condition")
        var condition: Condition,

        @Column(nullable = false, name = "address")
        var address: String,

        @Column(nullable = false, name = "squareFoot")
        var squareFoot: Int,

        @Column(nullable = false, name = "constructionDate")
        var constructionDate: Int,

        @OneToOne
        var style: Style,

        @Column(nullable = false, name = "rooms")
        var rooms: Int,

        @Column(nullable = false, name = "quarterBaths")
        var quarterBaths: Int,

        @Column(nullable = false, name = "halfBaths")
        var halfBaths: Int,

        @Column(nullable = false, name = "threeQuarterBaths")
        var threeQuarterBaths: Int,

        @Column(nullable = false, name = "fullBaths")
        var fullBaths: Int,

        @Column(nullable = false, name = "expenses")
        var expenses: Int
    )

    @Table(name = "style")
    @Entity
    class Style(

        @Column(name = "id")
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @Column(nullable = false, name = "label")
        var label: String
    )
}
