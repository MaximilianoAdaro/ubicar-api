package com.ubicar.ubicar.repositories.user

import com.ubicar.ubicar.entities.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: CrudRepository<User, Long> {

    override fun findAll(): List<User>

    @Query("select u from User u where u.email= ?1")
    fun findByEmail(email: String): Optional<User>

    fun existsByEmail(email: String): Boolean
}
