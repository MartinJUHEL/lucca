package com.martin.lucca.feature.employee.data.service

import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.commonmodel.user.EmployeeDetails
import com.martin.lucca.feature.employee.data.dto.EmployeeByDepartmentRequest
import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsRequest
import com.martin.lucca.feature.employee.data.dto.EmployeesRequest

/** Service to retrieve users. */
interface EmployeeService {
    sealed interface EmployeesResult {
        data class Success(val employees: List<Employee>) : EmployeesResult
        data object Error : EmployeesResult
    }

    sealed interface EmployeeDetailsResult {
        data class Success(val employeeDetails: EmployeeDetails) : EmployeeDetailsResult
        data object Error : EmployeeDetailsResult
    }

    suspend fun getEmployees(employeesRequest: EmployeesRequest): EmployeesResult

    suspend fun getEmployeeDetails(employeeDetailsRequest: EmployeeDetailsRequest): EmployeeDetailsResult

    suspend fun getEmployeeByDepartment(employeeByDepartmentRequest: EmployeeByDepartmentRequest): EmployeesResult
}