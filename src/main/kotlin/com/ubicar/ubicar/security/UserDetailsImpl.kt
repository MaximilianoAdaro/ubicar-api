package com.ubicar.ubicar.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ubicar.ubicar.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    val id: Long,
    val email: String,
    @JsonIgnore private val password: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    companion object {
        private const val serialVersionUID: Long = 1L

        fun build(user: User): UserDetailsImpl {
            return UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                listOf()
            )
        }
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
