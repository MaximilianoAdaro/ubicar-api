package com.ubicar.ubicar.factories.user

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.factories.AbstractFactory
import org.springframework.stereotype.Component

@Component
class UserDtoFactory : AbstractFactory<User, UserDTO> {

    override fun convert(input: User): UserDTO {
        return UserDTO(
            input.id,
            input.email,
            input.userName
        )
    }
}

@Component
class UserCreationFactory : AbstractFactory<User, UserCreationDTO> {

    override fun convert(input: User): UserCreationDTO {
        return UserCreationDTO(
            input.email,
            input.userName,
            input.password
        )
    }

    fun from(input: UserCreationDTO): User {
        return User(
            input.email,
            input.userName,
            input.password
        )
    }
}

@Component
class UserCreationGoogleFactory : AbstractFactory<User, GoogleLoginUserDTO> {

    override fun convert(input: User): GoogleLoginUserDTO {
        return GoogleLoginUserDTO(
            input.email,
            input.userName
        )
    }

    fun from(input: GoogleLoginUserDTO, password: String): User {
        return User(
            input.name,
            input.email,
            password
        )
    }

}
