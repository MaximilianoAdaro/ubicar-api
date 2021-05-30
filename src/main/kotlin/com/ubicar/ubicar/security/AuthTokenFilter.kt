package com.ubicar.ubicar.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(
    @Autowired val jwtUtils: JwtUtils,
    @Autowired val userDetailsService: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    companion object {
        private val infoLogger = LoggerFactory.getLogger(AuthTokenFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            val jwt = parseJwt(request)
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                val username: String = jwtUtils.getUserNameFromJwtToken(jwt)
                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            infoLogger.error("Cannot set user authentication: {}", e)
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): Pair<String, Boolean>? {
        if (request.cookies != null) {
            val jwtCookie = Arrays.stream(request.cookies).filter { cookie: Cookie -> cookie.name == "jwt" }
                .findFirst()
            val jwtGoogle = Arrays.stream(request.cookies).filter { cookie: Cookie -> cookie.name == "google-auth" }
                .findFirst()
            if (jwtCookie.isPresent) {
                val bool = if (jwtGoogle.isPresent) jwtGoogle.get().value.toBoolean() else false
                return Pair(jwtCookie.get().value, bool)
            }
        }
        return null
    }
}
