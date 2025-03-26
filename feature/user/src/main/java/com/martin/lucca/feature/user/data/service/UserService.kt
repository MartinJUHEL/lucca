package com.martin.lucca.feature.user.data.service

import com.martin.lucca.core.commonmodel.user.User
import com.martin.lucca.feature.user.data.dto.UsersRequest

/** Service to retrieve users. */
interface UserService {
    sealed interface UsersResult {
        data class Success(val users: List<User>) : UsersResult
        data object Error : UsersResult
    }

    suspend fun getUsers(usersRequest: UsersRequest): UsersResult

    suspend fun getUserDetails(userId: Int)
}