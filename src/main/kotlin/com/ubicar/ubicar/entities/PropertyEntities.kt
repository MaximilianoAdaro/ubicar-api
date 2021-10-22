package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

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
  var address: Address?,

  var squareFoot: Int?,

  var coveredSquareFoot: Int?,

  var levels: Int?,

  var constructionDate: Int?,

  @ManyToOne(cascade = [CascadeType.ALL])
  var style: Style?,

  var environments: Int?,

  var rooms: Int?,

  var toilets: Int?,

  var fullBaths: Int?,

  var expenses: Int?,

  // OPTIONAL FEATURES

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "property_amenity",
    joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "amenity_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var amenities: MutableList<Amenity> = mutableListOf(),

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "property_material",
    joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "material_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var materials: MutableList<ConstructionMaterial> = mutableListOf(),

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "property_security",
    joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "security_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var security: MutableList<SecurityMeasure> = mutableListOf(),

  var parkDescription: String?,

  @ElementCollection
  var links: MutableList<String>,

  @OneToMany(cascade = [CascadeType.ALL])
  var contacts: MutableList<Contact>,

  @OneToMany(cascade = [CascadeType.ALL])
  var openHouse: MutableList<OpenHouseDate>,

  var comments: String?,

  @CreationTimestamp
  var creationDate: LocalDate?,

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "user_like_property",
    joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var likes: MutableList<User> = mutableListOf(),

  @ManyToOne(cascade = [CascadeType.ALL])
  var owner: User,

  var step: Int,

  // Images
  @OneToMany(cascade = [CascadeType.ALL])
  var images: MutableList<Image> = mutableListOf(),

  // Tags de likeado
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "property_tag",
    joinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "tag_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var tags: MutableList<Tag> = mutableListOf(),

) : AbstractEntity()

@Table(name = "style")
@Entity
class Style(

  @Column(nullable = false)
  var label: String
) : AbstractEntity()

@Table(name = "amenity")
@Entity
class Amenity(
  @Column(nullable = false)
  var label: String,

  @ManyToMany(mappedBy = "amenities")
  @JsonBackReference
  private var properties: MutableList<Property> = mutableListOf()
) : AbstractEntity()

@Table(name = "material")
@Entity
class ConstructionMaterial(
  @Column(nullable = false)
  var label: String,

  @ManyToMany(mappedBy = "materials")
  @JsonBackReference
  private var properties: MutableList<Property> = mutableListOf()
) : AbstractEntity()

@Table(name = "security")
@Entity
class SecurityMeasure(
  @Column(nullable = false)
  var label: String,

  @ManyToMany(mappedBy = "security")
  @JsonBackReference
  private var properties: MutableList<Property> = mutableListOf()
) : AbstractEntity()

@Table(name = "contact")
@Entity
class Contact(
  @Column(nullable = false)
  var label: String,

  @Column(nullable = false)
  var email: String
) : AbstractEntity()

@Table(name = "open_house_date")
@Entity
class OpenHouseDate(
  @Column(nullable = false)
  var day: LocalDate,

  @Column(nullable = false)
  var initialTime: LocalTime,

  @Column(nullable = false)
  var finalTime: LocalTime
) : AbstractEntity()

@Table(name = "tag")
@Entity
class Tag(
  @Column(nullable = false)
  var value: String,

  @ManyToMany(mappedBy = "tags")
  @JsonBackReference
  private var properties: MutableList<Property> = mutableListOf()
) : AbstractEntity()
