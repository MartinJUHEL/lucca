package com.martin.lucca.feature.employee.data.service

import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.feature.employee.data.dto.EmployeesRequest

/** Service to retrieve users. */
interface EmployeeService {
    sealed interface EmployeesResult {
        data class Success(val employees: List<Employee>) : EmployeesResult
        data object Error : EmployeesResult
    }

    suspend fun getEmployees(employeesRequest: EmployeesRequest): EmployeesResult

    suspend fun getEmployeeDetails(userId: Int)
}