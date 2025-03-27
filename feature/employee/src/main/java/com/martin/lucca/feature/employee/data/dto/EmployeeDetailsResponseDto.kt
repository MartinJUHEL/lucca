package com.martin.lucca.feature.employee.data.dto

import java.time.LocalDateTime

// Root response DTO
data class EmployeeDetailsResponseDto(
    val data: EmployeeDataDto,
)

// Main user data
data class EmployeeDataDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val picture: PictureDto,
    val jobTitle: String,
    val department: DepartmentDto,
    val legalEntity: LegalEntityDto,
    val dtContractStart: LocalDateTime,
    val quote: String?,
    val mail: String,
    val manager: ManagerDto,
    val directLine: String,
    val birthDate: LocalDateTime
)

// Picture information
data class PictureDto(
    val name: String?,
    val href: String
)

// Department information
data class DepartmentDto(
    val name: String,
    val id: Long
)

// Legal entity information
data class LegalEntityDto(
    val name: String
)

// Manager information
data class ManagerDto(
    val id: Long,
    val name: String,
    val firstName: String,
    val lastName: String,
    val picture: PictureDto
)

