package com.ubicar.ubicar.entities

import javax.persistence.*

@Table(name = "user_data")
@Entity
class User(
    @Column(unique = true) var userName: String,
    @Column(unique = true) var email: String,
    var password: String

) : AbstractEntity() {
    companion object {
        var DEFAULT_PASSWORD = "password"
    }
}

class Role(
    @Column(unique = true) var userName: String,
    @Column(unique = true) var email: String,
    var password: String
) : AbstractEntity() {
    companion object {
        var DEFAULT_PASSWORD = "password"
    }
}