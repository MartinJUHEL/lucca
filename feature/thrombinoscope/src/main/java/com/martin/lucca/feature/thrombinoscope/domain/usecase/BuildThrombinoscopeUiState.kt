package com.martin.lucca.feature.thrombinoscope.domain.usecase

import com.martin.lucca.feature.thrombinoscope.presentation.ThrombinoscopeUiState
import com.martin.lucca.feature.user.data.service.UserService
import javax.inject.Inject

internal class BuildThrombinoscopeUiState @Inject constructor(private val userService: UserService) {

    suspend operator fun invoke(): ThrombinoscopeUiState {
        return when (val result = userService.getUsers(BuildGetUsersRequest().invoke())) {
            is UserService.UsersResult.Success -> {
                return if (result.users.isEmpty()) {
                    ThrombinoscopeUiState.Empty
                } else {
                    ThrombinoscopeUiState.Success(result.users)
                }
            }

            is UserService.UsersResult.Error -> {
                ThrombinoscopeUiState.Error
            }
        }
    }
}