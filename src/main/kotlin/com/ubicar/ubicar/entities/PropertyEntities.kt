package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDate
import java.time.LocalTime
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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private var type: TypeOfProperty,

    @OneToOne(cascade = [CascadeType.ALL])
    private var address: Address,

    @Column(nullable = false)
    private var squareFoot: Int,

    @Column(nullable = false)
    private var coveredSquareFoot: Int,

    @Column(nullable = false)
    private var levels: Int,

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
    private var expenses: Int,

    // OPTIONAL FEATURES

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "property_amenity",
        joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "amenity_id", referencedColumnName = "id")])
    @JsonManagedReference
    private var amenities: MutableList<Amenity> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "property_material",
        joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "material_id", referencedColumnName = "id")])
    @JsonManagedReference
    private var materials: MutableList<ConstructionMaterial> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "property_security",
        joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "security_id", referencedColumnName = "id")])
    @JsonManagedReference
    private var security: MutableList<SecurityMeasure> = mutableListOf(),

    private var parkDescription: String,

    @ElementCollection
    private var links: MutableList<String>,

    @OneToMany
    private var contacts: MutableList<Contact>,

    @OneToMany
    private var openHouse: MutableList<OpenHouseDate>,

    private var comments: String

) {
    fun getId(): Long = id
    fun getTitle(): String = title
    fun getPrice(): Int = price
    fun getCondition(): Condition = condition
    fun getType(): TypeOfProperty = type
    fun getAddress(): Address = address
    fun getSquareFoot(): Int = squareFoot
    fun getCoveredSquareFoot(): Int = coveredSquareFoot
    fun getLevels(): Int = levels
    fun getConstructionDate(): Int = constructionDate
    fun getStyle(): Style = style
    fun getRooms(): Int = rooms
    fun getQuarterBaths(): Int = quarterBaths
    fun getHalfBaths(): Int = halfBaths
    fun getThreeQuarterBaths(): Int = threeQuarterBaths
    fun getFullBaths(): Int = fullBaths
    fun getExpenses(): Int = expenses
    fun getAmenities(): MutableList<Amenity> = amenities
    fun getMaterials(): MutableList<ConstructionMaterial> = materials
    fun getSecurity(): MutableList<SecurityMeasure> = security
    fun getParkDescription(): String = parkDescription
    fun getLinks(): MutableList<String> = links
    fun getContacts(): MutableList<Contact> = contacts
    fun getOpenHouse(): MutableList<OpenHouseDate> = openHouse
    fun getComments(): String = comments
}

@Table(name = "style")
@Entity
class Style(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var label: String
)

@Table(name = "country")
@Entity
class Country(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var name: String
)

@Table(name = "state")
@Entity
class State(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var name: String,

    @ManyToOne
    private var country: Country
)

@Table(name = "city")
@Entity
class City(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var name: String,

    @ManyToOne
    private var state: State
)

@Table(name = "town")
@Entity
class Town(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var name: String,

    @ManyToOne
    private var city: City
)

@Table(name = "address")
@Entity
class Address(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @ManyToOne
    private var town: Town,

    @Column(nullable = false)
    private var postalCode: String,

    @Column(nullable = false)
    private var street: String,

    @Column(nullable = false)
    private var number: Int,

    @Column(nullable = false)
    private var department: String
)

@Table(name = "amenity")
@Entity
data class Amenity(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var label: String,

    @ManyToMany(mappedBy = "amenities")
//    @JsonIgnoreProperties("amenities")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
)

@Table(name = "material")
@Entity
data class ConstructionMaterial (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var label: String,

    @ManyToMany(mappedBy = "materials")
//    @JsonIgnoreProperties("materials")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
)

@Table(name = "security")
@Entity
data class SecurityMeasure (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var label: String,

    @ManyToMany(mappedBy = "security")
//    @JsonIgnoreProperties("security")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
)

@Table(name = "contact")
@Entity
class Contact (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var label: String,

    @Column(nullable = false)
    private var email: String
)

@Table(name = "open_house_date")
@Entity
class OpenHouseDate (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    @Column(nullable = false)
    private var day: LocalDate,

    @Column(nullable = false)
    private var initialTime: LocalTime,

    @Column(nullable = false)
    private var finalTime: LocalTime
)