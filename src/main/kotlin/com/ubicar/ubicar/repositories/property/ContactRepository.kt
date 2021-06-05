package com.ubicar.ubicar.repositories.property

import com.ubicar.ubicar.entities.Contact
import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, Long> {}
