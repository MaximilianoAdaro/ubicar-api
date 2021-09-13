package com.ubicar.ubicar.security

import org.apache.velocity.app.VelocityEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.Properties

@EnableWebSecurity
@Configuration
class SecurityConfig @Autowired constructor(
  private val userDetailsService: UserDetailsServiceImpl,
  private val unauthorizedHandler: AuthEntryPointJwt,
  private val authTokenFilter: AuthTokenFilter
) : WebSecurityConfigurerAdapter() {

  public override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
    authenticationManagerBuilder.userDetailsService<UserDetailsService>(userDetailsService)
      .passwordEncoder(passwordEncoder())
  }

  @Bean
  override fun authenticationManagerBean(): AuthenticationManager {
    return super.authenticationManagerBean()
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  override fun configure(http: HttpSecurity) {
//        val repository = CookieCsrfTokenRepository()
//        repository.setCookieHttpOnly(true)
//        repository.setSecure(true)
    http.csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
      .antMatchers("/auth/**").permitAll()
      .antMatchers("/geo-data/**").permitAll()
      .antMatchers("/public/**").permitAll()
      // SWAGGER CONFIG
      .antMatchers(
        "/v3/api-docs", "/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
        "/swagger-ui/**", "/swagger-ui.html", "/swagger-ui.html**", "/swagger-ui.html/**",
        "/swagger-ui.html#/**", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui/**",
        "/v3/api-docs/**", "/v2/api-docs/**"
      ).permitAll()
      .anyRequest().authenticated()
      .and().headers().xssProtection()

    http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)

    http.oauth2ResourceServer().jwt()
  }

  @Bean
  fun velocityEngine(): VelocityEngine? {
    val properties = Properties()
    properties.setProperty("input.encoding", "UTF-8")
    properties.setProperty("output.encoding", "UTF-8")
    properties.setProperty("resource.loader", "class")
    properties.setProperty(
      "class.resource.loader.class",
      "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
    )
    return VelocityEngine(properties)
  }
}
