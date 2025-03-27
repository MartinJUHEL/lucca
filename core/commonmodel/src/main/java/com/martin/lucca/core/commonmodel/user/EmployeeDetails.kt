package com.martin.lucca.core.commonmodel.user

import java.time.LocalDateTime

data class EmployeeDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val pictureName: String?,
    val pictureUrl: String?,
    val jobTitle: String,
    val departmentName: String,
    val departmentId: Int,
    val legalEntityName: String,
    val dtContractStart: LocalDateTime,
    val quote: String?,
    val mail: String,
    val manager: Employee,
    val directLine: String,
    val birthDate: LocalDateTime
)
