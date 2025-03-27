package com.martin.lucca.feature.employee.data.service

import com.martin.lucca.core.common.network.FetchedResponse
import com.martin.lucca.feature.employee.data.api.EmployeeApi
import com.martin.lucca.feature.employee.data.dto.EmployeesRequest
import com.martin.network.SafeHttpCaller
import com.martin.lucca.feature.employee.data.dto.toUsersList
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
        }, transform = { userResponseDto -> userResponseDto.toUsersList() })

        return when (response) {
            is FetchedResponse.Success -> EmployeeService.EmployeesResult.Success(response.value)
            is FetchedResponse.Error -> EmployeeService.EmployeesResult.Error
        }
    }

    override suspend fun getEmployeeDetails(userId: Int) {
        TODO("Not yet implemented")
    }


}