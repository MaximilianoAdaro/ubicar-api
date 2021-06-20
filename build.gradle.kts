
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
  id("org.springframework.boot") version "2.4.5"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  id("org.asciidoctor.convert") version "1.5.8"
  id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
  kotlin("jvm") version "1.4.32"
  kotlin("plugin.spring") version "1.4.32"
  kotlin("plugin.jpa") version "1.4.32"
  kotlin("plugin.allopen") version "1.4.32"
}

group = "com.ubicar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
  jcenter()
}

extra["snippetsDir"] = file("build/generated-snippets")

configurations {
  ktlint
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web-services")
  implementation("org.springframework.boot:spring-boot-starter-data-rest")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("junit:junit:4.13.1")
  implementation("io.jsonwebtoken:jjwt:0.9.1")
  implementation("com.google.firebase:firebase-admin:6.12.2")
  runtimeOnly("com.h2database:h2")
  runtimeOnly("org.postgresql:postgresql")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.security:spring-security-test")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")
  implementation("io.springfox:springfox-swagger2:3.0.0")
  implementation("com.pinterest:ktlint:0.41.0")
}

allOpen {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

ktlint {
  debug.set(true)
  verbose.set(true)
  outputColorName.set("RED")
  ignoreFailures.set(true)
  disabledRules.set(setOf("final-newline", "no-wildcard-imports", "import-ordering", "chain-wrapping"))
  reporters {
    reporter(ReporterType.CHECKSTYLE)
    reporter(ReporterType.JSON)
    reporter(ReporterType.HTML)
  }
  filter {
    exclude("**/style-violations.kt")
    exclude("**/generated/**")
    include("**/kotlin/**")
  }
}
