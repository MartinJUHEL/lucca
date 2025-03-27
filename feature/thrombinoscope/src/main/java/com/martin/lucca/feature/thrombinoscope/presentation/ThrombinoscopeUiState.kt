package com.martin.lucca.feature.thrombinoscope.presentation

import com.martin.lucca.core.commonmodel.user.Employee

internal sealed interface ThrombinoscopeUiState {
    data object Loading : ThrombinoscopeUiState
    data object Error : ThrombinoscopeUiState
    data class Success(val employees: List<Employee>, val isRefreshing: Boolean = false) : ThrombinoscopeUiState

    data object Empty : ThrombinoscopeUiState
}