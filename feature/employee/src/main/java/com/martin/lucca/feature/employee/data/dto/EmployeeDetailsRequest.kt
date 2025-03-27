package com.martin.lucca.feature.employee.data.dto

data class EmployeeDetailsRequest(
    val employeeId: Int,
    val fields: String,
)