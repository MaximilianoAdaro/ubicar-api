package com.ubicar.ubicar.services.contact

import com.ubicar.ubicar.entities.Contact
import com.ubicar.ubicar.repositories.property.ContactRepository
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl(private val contactRepository: ContactRepository): ContactService {

    override fun save(contact: Contact): Contact {
        return contactRepository.save(contact)
    }

    override fun findById(id: String): Contact {
        return contactRepository.findById(id).orElseThrow()
    }

    override fun delete(id: String) {
        contactRepository.delete(findById(id))
    }
}
