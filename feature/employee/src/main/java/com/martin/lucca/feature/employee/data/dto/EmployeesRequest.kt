package com.martin.lucca.feature.employee.data.dto

data class EmployeesRequest(
    val appInstanceId: Int,
    val operations: Int,
    val fields: String,
    val orderBy: String
)