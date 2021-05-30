package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "users")
@Entity
class User(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long,

    private var email: String,

    private var password: String
) {
    fun getId() = id
    fun getEmail() = email
    fun getPassword() = password
    fun setPassword(password: String) { apply { this.password = password }}
}
