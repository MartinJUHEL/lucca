package com.martin.lucca.feature.user.data.api

import com.martin.lucca.feature.user.data.dto.UsersResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    /**
     * Get list of users with specified filters and fields
     * @return list of users with some details
     */
    @GET("/api/v3/users/scope")
    suspend fun getUsers(
        @Query("appInstanceId") appInstanceId: Int,
        @Query("operations") operations: Int,
        @Query("fields") fields: String,
        @Query("orderBy") orderBy: String
    ): Response<UsersResponseDto>
}