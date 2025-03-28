package com.martin.lucca.feature.userdetails.presentation

import com.martin.lucca.core.commonmodel.user.Employee

internal sealed class DepartmentEmployeesUiState {
    data object Loading : DepartmentEmployeesUiState()
    data class Success(val employees: List<Employee>) : DepartmentEmployeesUiState()
    data object Error : DepartmentEmployeesUiState()
}