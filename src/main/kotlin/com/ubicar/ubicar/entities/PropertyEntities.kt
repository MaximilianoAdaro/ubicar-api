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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var price: Int,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var condition: Condition,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var type: TypeOfProperty,

    @OneToOne(cascade = [CascadeType.ALL])
    var address: Address,

    @Column(nullable = false)
    var squareFoot: Int,

    @Column(nullable = false)
    var coveredSquareFoot: Int,

    @Column(nullable = false)
    var levels: Int,

    @Column(nullable = false)
    var constructionDate: Int,

    @ManyToOne
    var style: Style,

    @Column(nullable = false)
    var environments: Int,

    @Column(nullable = false)
    var rooms: Int,

    @Column(nullable = false)
    var toilets: Int,

    @Column(nullable = false)
    var fullBaths: Int,

    @Column(nullable = false)
    var expenses: Int,

    // OPTIONAL FEATURES

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "property_amenity",
        joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "amenity_id", referencedColumnName = "id")])
    @JsonManagedReference
    var amenities: MutableList<Amenity> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "property_material",
        joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "material_id", referencedColumnName = "id")])
    @JsonManagedReference
    var materials: MutableList<ConstructionMaterial> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "property_security",
        joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "security_id", referencedColumnName = "id")])
    @JsonManagedReference
    var security: MutableList<SecurityMeasure> = mutableListOf(),

    var parkDescription: String,

    @ElementCollection
    var links: MutableList<String>,

    @OneToMany
    var contacts: MutableList<Contact>,

    @OneToMany
    var openHouse: MutableList<OpenHouseDate>,

    var comments: String
)

@Table(name = "style")
@Entity
class Style(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var label: String
)

@Table(name = "country")
@Entity
class Country(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var name: String
)

@Table(name = "state")
@Entity
class State(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @ManyToOne
    var country: Country
)

@Table(name = "city")
@Entity
class City(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @ManyToOne
    var state: State
)

@Table(name = "town")
@Entity
class Town(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @ManyToOne
    var city: City
)

@Table(name = "address")
@Entity
class Address(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

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
)

@Table(name = "amenity")
@Entity
class Amenity(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var label: String,

    @ManyToMany(mappedBy = "amenities")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
)

@Table(name = "material")
@Entity
class ConstructionMaterial (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var label: String,

    @ManyToMany(mappedBy = "materials")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
)

@Table(name = "security")
@Entity
class SecurityMeasure (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var label: String,

    @ManyToMany(mappedBy = "security")
    @JsonBackReference
    private var properties: MutableList<Property> = mutableListOf()
)

@Table(name = "contact")
@Entity
class Contact (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var label: String,

    @Column(nullable = false)
    var email: String
)

@Table(name = "open_house_date")
@Entity
class OpenHouseDate (

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @Column(nullable = false)
    var day: LocalDate,

    @Column(nullable = false)
    var initialTime: LocalTime,

    @Column(nullable = false)
    var finalTime: LocalTime
)
