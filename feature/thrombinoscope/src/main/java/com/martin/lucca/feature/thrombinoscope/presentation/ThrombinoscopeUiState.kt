package com.martin.lucca.feature.thrombinoscope.presentation

import com.martin.lucca.core.commonmodel.user.User

internal sealed interface ThrombinoscopeUiState {
    data object Loading : ThrombinoscopeUiState
    data object Error : ThrombinoscopeUiState
    data class Success(val users: List<User>) : ThrombinoscopeUiState
    data object Empty : ThrombinoscopeUiState
}