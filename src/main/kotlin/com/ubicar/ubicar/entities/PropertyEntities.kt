package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Table(name = "property")
@Entity
class Property(

    // REQUIRED FEATURES

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
    private var environments: Int,

    @Column(nullable = false)
    private var rooms: Int,

    @Column(nullable = false)
    private var toilets: Int,

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

): AbstractEntity() {
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
    fun getEnvironments(): Int = environments
    fun getRooms(): Int = rooms
    fun getToilets(): Int = toilets
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

    @Column(nullable = false)
    var label: String
): AbstractEntity()

@Table(name = "country")
@Entity
class Country(
    @Column(nullable = false)
    var name: String
): AbstractEntity()

@Table(name = "state")
@Entity
class State(
    @Column(nullable = false)
    var name: String,

    @ManyToOne
    var country: Country
): AbstractEntity()

@Table(name = "city")
@Entity
class City(

    @Column(nullable = false)
    var name: String,

    @ManyToOne
    var state: State
): AbstractEntity()

@Table(name = "town")
@Entity
class Town(
    @Column(nullable = false)
    var name: String,

    @ManyToOne
    var city: City
): AbstractEntity()

@Table(name = "address")
@Entity
class Address(
    @ManyToOne
    var town: Town,

    @Column(nullable = false)
    var postalCode: String,

    @Column(nullable = false)
    var street: String,

    @Column(nullable = false)
    var number: Int,

    @Column(nullable = false)
    var department: String
): AbstractEntity()

@Table(name = "amenity")
@Entity
class Amenity(
    @Column(nullable = false)
    var label: String,

    @ManyToMany(mappedBy = "amenities")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
): AbstractEntity()

@Table(name = "material")
@Entity
class ConstructionMaterial (
    @Column(nullable = false)
    var label: String,

    @ManyToMany(mappedBy = "materials")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
): AbstractEntity()

@Table(name = "security")
@Entity
class SecurityMeasure (
    @Column(nullable = false)
    var label: String,

    @ManyToMany(mappedBy = "security")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
): AbstractEntity()

@Table(name = "contact")
@Entity
class Contact (
    @Column(nullable = false)
    var label: String,

    @Column(nullable = false)
    var email: String
): AbstractEntity()

@Table(name = "open_house_date")
@Entity
class OpenHouseDate (
    @Column(nullable = false)
    var day: LocalDate,

    @Column(nullable = false)
    var initialTime: LocalTime,

    @Column(nullable = false)
    var finalTime: LocalTime
): AbstractEntity()
