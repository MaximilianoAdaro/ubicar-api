package com.ubicar.ubicar.loaders

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.ubicar.ubicar.entities.Department
import com.ubicar.ubicar.loaders.loadersDto.AbstractMethod
import com.ubicar.ubicar.loaders.loadersDto.DepartmentJson
import com.ubicar.ubicar.repositories.location.DepartmentRepository
import com.ubicar.ubicar.repositories.location.StateRepository
import com.ubicar.ubicar.utils.NotFoundException
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!test")
@Component
class DepartmentLoader(
  private val departmentRepository: DepartmentRepository,
  private val stateRepository: StateRepository
) :
  CommandLineRunner, Ordered {

  override fun run(vararg args: String?) {
    if (departmentRepository.totalAmount() > 1) return
    val departments: List<Department> = getAllDepartmentsFromFile()
    departments.forEach { department ->
      departmentRepository.findFirstByGid(department.gid)
        .orElseGet { departmentRepository.save(department) }
    }
  }

  private fun getAllDepartmentsFromFile(): List<Department> {
    val mapper = jacksonObjectMapper()
    mapper.registerKotlinModule()
    mapper.registerModule(JavaTimeModule())

    val content = AbstractMethod.getContentFromPath("/geoRef/departments.json")
    val jsonTextList: DepartmentJson = mapper.readValue(content)
    return jsonTextList.departamentos.map { departments ->
      val state = stateRepository.findFirstByGid(departments.provincia.id)
        .orElseThrow { NotFoundException("State not found") }
      departments.toDepartment(state)
    }
  }

  override fun getOrder(): Int {
    return 13
  }
}
