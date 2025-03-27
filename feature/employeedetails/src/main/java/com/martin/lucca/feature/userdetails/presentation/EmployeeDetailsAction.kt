package com.martin.lucca.feature.userdetails.presentation

internal sealed interface EmployeeDetailsAction {
    data object Load : EmployeeDetailsAction
}