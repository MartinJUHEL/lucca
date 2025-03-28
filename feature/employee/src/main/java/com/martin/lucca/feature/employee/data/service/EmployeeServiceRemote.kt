package com.martin.lucca.feature.employee.data.service

import com.martin.lucca.core.common.network.FetchedResponse
import com.martin.lucca.feature.employee.data.api.EmployeeApi
import com.martin.lucca.feature.employee.data.dto.EmployeeByDepartmentRequest
import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsRequest
import com.martin.lucca.feature.employee.data.dto.EmployeesRequest
import com.martin.lucca.feature.employee.data.dto.toEmployeeDetails
import com.martin.lucca.feature.employee.data.dto.toEmployeesList
import com.martin.network.SafeHttpCaller
import javax.inject.Inject

class EmployeeServiceRemote @Inject constructor(
    private val employeeApi: EmployeeApi,
    private val safeHttpCaller: SafeHttpCaller
) : EmployeeService {

    override suspend fun getEmployees(employeesRequest: EmployeesRequest): EmployeeService.EmployeesResult {
        val response = safeHttpCaller.call(action = {
            employeeApi.getEmployees(
                appInstanceId = employeesRequest.appInstanceId,
                operations = employeesRequest.operations,
                fields = employeesRequest.fields,
                orderBy = employeesRequest.orderBy
            )
        }, transform = { employeesResponseDto -> employeesResponseDto.toEmployeesList() })

        return when (response) {
            is FetchedResponse.Success -> EmployeeService.EmployeesResult.Success(response.value)
            is FetchedResponse.Error -> EmployeeService.EmployeesResult.Error
        }
    }

    override suspend fun getEmployeeDetails(employeeDetailsRequest: EmployeeDetailsRequest): EmployeeService.EmployeeDetailsResult {
        val response = safeHttpCaller.call(action = {
            employeeApi.getEmployee(
                fields = employeeDetailsRequest.fields,
                employeeId = employeeDetailsRequest.employeeId
            )
        }, transform = { employeeResponseDto -> employeeResponseDto.toEmployeeDetails() })

        return when (response) {
            is FetchedResponse.Success -> EmployeeService.EmployeeDetailsResult.Success(response.value)
            is FetchedResponse.Error -> EmployeeService.EmployeeDetailsResult.Error
        }
    }

    override suspend fun getEmployeeByDepartment(employeeByDepartmentRequest: EmployeeByDepartmentRequest): EmployeeService.EmployeesResult {
        val response = safeHttpCaller.call(action = {
            employeeApi.getEmployeeByDepartment(
                fields = employeeByDepartmentRequest.fields,
                departmentId = employeeByDepartmentRequest.departmentId
            )
        }, transform = { employeesResponseDto -> employeesResponseDto.toEmployeesList() })

        return when (response) {
            is FetchedResponse.Success -> EmployeeService.EmployeesResult.Success(response.value)
            is FetchedResponse.Error -> EmployeeService.EmployeesResult.Error
        }
    }
}