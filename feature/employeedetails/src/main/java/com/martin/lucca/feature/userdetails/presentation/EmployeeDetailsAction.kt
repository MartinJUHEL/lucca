package com.martin.lucca.feature.userdetails.presentation

internal sealed interface EmployeeDetailsAction {
    data object Load : EmployeeDetailsAction
    data class OnEmployeeClicked(val employeeId: Int) : EmployeeDetailsAction
}