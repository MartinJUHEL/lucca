package com.martin.lucca.feature.user.data.dto

data class UsersRequest(
    val appInstanceId: Int,
    val operations: Int,
    val fields: String,
    val orderBy: String
)