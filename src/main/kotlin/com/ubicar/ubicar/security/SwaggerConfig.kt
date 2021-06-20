package com.ubicar.ubicar.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalTime

@Configuration
@EnableSwagger2
class SwaggerConfig {
  @Bean
  fun api(): Docket {
    return Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .securityContexts(listOf(securityContext()))
//            .securitySchemes(listOf(apiKey()))
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build()
      .directModelSubstitute(LocalTime::class.java, String::class.java)
  }
//
//    private fun apiInfo(): ApiInfo {
//        return ApiInfo(
//            "My REST API",
//            "Some custom description of API.",
//            "1.0",
//            "Terms of service",
//            Contact("Ubicar Client", "www.ubicar.com", "ubicar@gmail.com"),
//            "License of API",
//            "API license URL",
//            Collections.emptyList()
//        )
//    }
//
//    private fun apiKey(): ApiKey {
//        return ApiKey("JWT", "Authorization", "header")
//    }
//
//    private fun securityContext(): SecurityContext? {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build()
//    }
//
//    private fun defaultAuth(): List<SecurityReference?> {
//        val authorizationScope = AuthorizationScope("global", "accessEverything")
//        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls(1)
//        authorizationScopes[0] = authorizationScope
//        return listOf(SecurityReference("JWT", authorizationScopes))
//    }
}
