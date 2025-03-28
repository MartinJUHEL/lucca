package com.martin.lucca.feature.userdetails.domain

import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.userdetails.presentation.DepartmentEmployeesUiState
import javax.inject.Inject

internal class BuildDepartmentEmployeesUiState @Inject constructor(
    private val employeeService: EmployeeService,
    private val buildGetDepartmentEmployeesRequest: BuildGetDepartmentEmployeesRequest
) {

    suspend operator fun invoke(departmentId: Int): DepartmentEmployeesUiState {
        return when (val result =
            employeeService.getEmployeeByDepartment(buildGetDepartmentEmployeesRequest(departmentId))) {
            is EmployeeService.EmployeesResult.Success -> DepartmentEmployeesUiState.Success(result.employees)

            is EmployeeService.EmployeesResult.Error -> DepartmentEmployeesUiState.Error
        }
    }
}