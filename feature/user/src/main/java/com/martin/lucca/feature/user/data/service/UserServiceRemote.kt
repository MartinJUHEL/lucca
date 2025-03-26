package com.martin.lucca.feature.user.data.service

import com.martin.lucca.core.common.network.FetchedResponse
import com.martin.lucca.feature.user.data.api.UserApi
import com.martin.lucca.feature.user.data.dto.UsersRequest
import com.martin.network.SafeHttpCaller
import com.martin.lucca.feature.user.data.dto.toUsersList
import javax.inject.Inject

class UserServiceRemote @Inject constructor(
    private val userApi: UserApi,
    private val safeHttpCaller: SafeHttpCaller
) : UserService {

    override suspend fun getUsers(usersRequest: UsersRequest): UserService.UsersResult {
        val response = safeHttpCaller.call(action = {
            userApi.getUsers(
                appInstanceId = usersRequest.appInstanceId,
                operations = usersRequest.operations,
                fields = usersRequest.fields,
                orderBy = usersRequest.orderBy
            )
        }, transform = { userResponseDto -> userResponseDto.toUsersList() })

        return when (response) {
            is FetchedResponse.Success -> UserService.UsersResult.Success(response.value)
            is FetchedResponse.Error -> UserService.UsersResult.Error
        }
    }

    override suspend fun getUserDetails(userId: Int) {
        TODO("Not yet implemented")
    }


}