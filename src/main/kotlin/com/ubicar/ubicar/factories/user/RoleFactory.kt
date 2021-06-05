package com.ubicar.ubicar.factories.user

import com.ubicar.ubicar.dtos.RoleDTO
import com.ubicar.ubicar.entities.UserRole
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class RoleFactory: AbstractFactory<UserRole, RoleDTO> {

    override fun convert(input: UserRole): RoleDTO {
        return RoleDTO(input.id, input.title)
    }
}
