package com.martin.lucca.feature.thrombinoscope.domain.usecase

import com.martin.lucca.feature.thrombinoscope.presentation.ThrombinoscopeUiState
import com.martin.lucca.feature.employee.data.service.EmployeeService
import javax.inject.Inject

internal class BuildThrombinoscopeUiState @Inject constructor(private val employeeService: EmployeeService) {

    suspend operator fun invoke(): ThrombinoscopeUiState {
        return when (val result = employeeService.getEmployees(BuildGetUsersRequest().invoke())) {
            is EmployeeService.EmployeesResult.Success -> {
                return if (result.employees.isEmpty()) {
                    ThrombinoscopeUiState.Empty
                } else {
                    ThrombinoscopeUiState.Success(result.employees)
                }
            }

            is EmployeeService.EmployeesResult.Error -> {
                ThrombinoscopeUiState.Error
            }
        }
    }
}