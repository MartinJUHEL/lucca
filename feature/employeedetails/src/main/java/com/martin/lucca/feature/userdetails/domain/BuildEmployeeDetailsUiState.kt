package com.martin.lucca.feature.userdetails.domain

import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.userdetails.presentation.EmployeeDetailsUiState
import javax.inject.Inject

internal class BuildEmployeeDetailsUiState @Inject constructor(
    private val employeeService: EmployeeService,
    private val buildGetEmployeeDetailsRequest: BuildGetEmployeeDetailsRequest
) {

    suspend operator fun invoke(employeeId: Int): EmployeeDetailsUiState {
        return when (val result =
            employeeService.getEmployeeDetails(buildGetEmployeeDetailsRequest(employeeId))) {
            is EmployeeService.EmployeeDetailsResult.Success -> EmployeeDetailsUiState.Success(
                result.employeeDetails
            )

            is EmployeeService.EmployeeDetailsResult.Error -> EmployeeDetailsUiState.Error
        }
    }
}