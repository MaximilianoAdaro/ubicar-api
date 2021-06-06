package com.ubicar.ubicar.factories.property

import com.ubicar.ubicar.dtos.ContactDto
import com.ubicar.ubicar.dtos.OpenHouseDateDto
import com.ubicar.ubicar.entities.Contact
import com.ubicar.ubicar.entities.OpenHouseDate
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class ContactFactory: AbstractFactory<Contact, ContactDto> {
    override fun convert(input: Contact): ContactDto {
        return ContactDto(input.label, input.email)
    }

    fun from(input: ContactDto): Contact {
        return Contact(input.label, input.email)
    }

}

@Component
class OpenHouseDateFactory: AbstractFactory<OpenHouseDate, OpenHouseDateDto> {
    override fun convert(input: OpenHouseDate): OpenHouseDateDto {
        return OpenHouseDateDto(input.day, input.initialTime, input.finalTime)
    }

    fun from(input: OpenHouseDateDto): OpenHouseDate {
        return OpenHouseDate(input.day, input.initialTime, input.finalTime)
    }
}