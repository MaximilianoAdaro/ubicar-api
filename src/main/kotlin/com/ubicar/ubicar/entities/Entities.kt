package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "property")
@Entity
class Property(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long = 0,

    // REQUIRED FEATURES

    @Column(nullable = false)
    var price: Int,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var condition: Condition,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var squareFoot: Int,

    @Column(nullable = false)
    var constructionDate: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    var style: Style,

    @Column(nullable = false)
    var rooms: Int,

    @Column(nullable = false)
    var quarterBaths: Int,

    @Column(nullable = false)
    var halfBaths: Int,

    @Column(nullable = false)
    var threeQuarterBaths: Int,

    @Column(nullable = false)
    var fullBaths: Int,

    @Column(nullable = false)
    var expenses: Int,

)

@Table(name = "style")
@Entity
class Style(

    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false)
    var label: String
)
