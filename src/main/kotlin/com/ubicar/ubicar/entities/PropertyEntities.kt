package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Table(name = "property")
@Entity
class Property(

    // REQUIRED FEATURES

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

    @ManyToOne(cascade = [CascadeType.ALL])
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

    @OneToMany(cascade = [CascadeType.ALL])
    var contacts: MutableList<Contact>,

    @OneToMany(cascade = [CascadeType.ALL])
    var openHouse: MutableList<OpenHouseDate>,

    var comments: String,

    @CreationTimestamp
    var creationDate: LocalDate,

    @ManyToMany(mappedBy = "likedProperties")
    @JsonBackReference
    var likes: MutableList<User> = mutableListOf()

): AbstractEntity()

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
