package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "property")
@Entity
data class Property(

    // REQUIRED FEATURES

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var title: String,

    @Column(nullable = false)
    private var price: Int,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private var condition: Condition,

    @OneToOne(cascade = [CascadeType.ALL])
    private var address: Address,

    @Column(nullable = false)
    private var squareFoot: Int,

    @Column(nullable = false)
    private var constructionDate: Int,

    @ManyToOne
    private var style: Style,

    @Column(nullable = false)
    private var rooms: Int,

    @Column(nullable = false)
    private var quarterBaths: Int,

    @Column(nullable = false)
    private var halfBaths: Int,

    @Column(nullable = false)
    private var threeQuarterBaths: Int,

    @Column(nullable = false)
    private var fullBaths: Int,

    @Column(nullable = false)
    private var expenses: Int
) {
    fun getId(): Long = id
    fun getTitle(): String = title
    fun getPrice(): Int = price
    fun getCondition(): Condition = condition
    fun getAddress(): Address = address
    fun getSquareFoot(): Int = squareFoot
    fun getConstructionDate(): Int = constructionDate
    fun getStyle(): Style = style
    fun getRooms(): Int = rooms
    fun getQuarterBaths(): Int = quarterBaths
    fun getHalfBaths(): Int = halfBaths
    fun getThreeQuarterBaths(): Int = threeQuarterBaths
    fun getFullBaths(): Int = fullBaths
    fun getExpenses(): Int = expenses
}

@Table(name = "style")
@Entity
class Style(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var label: String
)

@Table(name = "address")
@Entity
class Address(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var country: String,

    @Column(nullable = false)
    private var state: String,

    @Column(nullable = false)
    private var city: String,

    @Column(nullable = false)
    private var neighbourhood: String,

    @Column(nullable = false)
    private var postalCode: String,

    @Column(nullable = false)
    private var street: String,

    @Column(nullable = false)
    private var number: Int,

    @Column(nullable = false)
    private var department: String
)
