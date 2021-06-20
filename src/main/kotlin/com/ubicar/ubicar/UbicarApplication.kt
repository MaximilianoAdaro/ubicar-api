package com.ubicar.ubicar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableJpaRepositories
@EnableScheduling
class UbicarApplication

fun main(args: Array<String>) {
  runApplication<UbicarApplication>(*args)
}
