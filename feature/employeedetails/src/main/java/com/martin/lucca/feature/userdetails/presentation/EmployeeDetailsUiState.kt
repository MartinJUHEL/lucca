package com.martin.lucca.feature.userdetails.presentation

import com.martin.lucca.core.commonmodel.user.EmployeeDetails

internal sealed interface EmployeeDetailsUiState {
    data object Loading : EmployeeDetailsUiState
    data object Error : EmployeeDetailsUiState
    data class Success(val employee: EmployeeDetails) : EmployeeDetailsUiState
}