package com.ubicar.ubicar.factories.user

import com.ubicar.ubicar.dtos.GoogleLoginUserDTO
import com.ubicar.ubicar.dtos.UserCreationDTO
import com.ubicar.ubicar.dtos.UserDTO
import com.ubicar.ubicar.dtos.UserRoleDto
import com.ubicar.ubicar.entities.User
import com.ubicar.ubicar.entities.UserOrigin
import com.ubicar.ubicar.entities.UserRole
import com.ubicar.ubicar.factories.AbstractFactory
import com.ubicar.ubicar.repositories.user.UserRoleRepository
import com.ubicar.ubicar.services.user.UserRoleService
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
class UserCreationFactory(
    val userRoleRepository: UserRoleRepository,
) : AbstractFactory<User, UserCreationDTO> {

    override fun convert(input: User): UserCreationDTO {
        return UserCreationDTO(
            email = input.email,
            userName = input.userName,
            password = input.password,
            userRole = input.userRole.id
        )
    }

    fun from(input: UserCreationDTO): User {
        val userRole: UserRole = userRoleRepository.findById(input.userRole).get()
        return User(
            userName = input.userName,
            email = input.email,
            password = input.password,
            userOrigin = UserOrigin.UBICAR,
            userRole = userRole
        )
    }
}

@Component
class UserCreationGoogleFactory(
    val userRoleService: UserRoleService
) : AbstractFactory<User, GoogleLoginUserDTO> {

    override fun convert(input: User): GoogleLoginUserDTO {
        return GoogleLoginUserDTO(
            name = input.userName,
            email = input.email
        )
    }

    fun from(input: GoogleLoginUserDTO, password: String?): User {
        return User(
            userName = input.name,
            email = input.email,
            password = password,
            userOrigin = UserOrigin.GOOGLE,
            userRole = userRoleService.getDefault()
        )
    }
}


@Component
class UserRoleDtoFactory : AbstractFactory<UserRole, UserRoleDto> {

    override fun convert(input: UserRole): UserRoleDto {
        return UserRoleDto(
            input.id,
            input.title,
            input.slug,
            input.description,
            input.active,
            input.creationDate,
        )
    }

    fun from(input: UserRoleDto): UserRole {
        return UserRole(
            input.title,
            input.slug,
            input.description,
            input.active,
            input.creationDate,
        )
    }

}
