package com.martin.lucca.feature.employee.data.dto

data class EmployeeByDepartmentRequest(
    val fields: String,
    val departmentId: Int
)