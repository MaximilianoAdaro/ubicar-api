package com.ubicar.ubicar.factories.user

import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.factories.AbstractFactory

class UserFactory: AbstractFactory<User, UserDTO> {

    override fun convert(input: User): UserDTO {
        return UserDTO(
            input.getId(),
            input.getEmail(),
        )
    }
}
