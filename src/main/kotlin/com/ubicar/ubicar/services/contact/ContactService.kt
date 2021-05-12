package com.ubicar.ubicar.services.contact

import com.ubicar.ubicar.entities.Contact

interface ContactService {
    fun save(contact: Contact) : Contact

    fun findById(id: Long) : Contact

    fun delete(contact: Long)
}
