package com.ubicar.ubicar.security

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import java.security.SignatureException
import com.google.firebase.auth.FirebaseAuth


@Component
class JwtUtils {

    private val jwtSecret: String = "secret_key"

    private val jwtExpirationMs = 1000000000000000000

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetailsImpl
        return Jwts.builder()
            .setSubject(userPrincipal.email)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserNameFromJwtToken(token: Pair<String, Boolean>): String {
        return if (token.second) {
//            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.first)
//            println(decodedToken.uid)
//            decodedToken.email
            "asdf"
        } else Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token.first).body.subject
    }

    fun validateJwtToken(authToken: Pair<String, Boolean>): Boolean {
        try {
            if (authToken.second) {
//                val decodedToken = FirebaseAuth.getInstance().verifyIdToken(authToken.first)
//                println(decodedToken.uid)
            } else Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken.first)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}

