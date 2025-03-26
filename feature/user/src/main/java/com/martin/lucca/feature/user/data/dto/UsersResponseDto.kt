package com.martin.lucca.feature.user.data.dto

import com.martin.lucca.core.commonmodel.user.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponseDto(
    val data: DataResponseDto,
)

@JsonClass(generateAdapter = true)
data class DataResponseDto(
    val items: List<UserItemResponseDto>
)

@JsonClass(generateAdapter = true)
data class UserItemResponseDto(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String,
    val jobTitle: String?,
    val picture: PictureItemResponseDto?
)

@JsonClass(generateAdapter = true)
data class PictureItemResponseDto(
    val name: String,
    val href: String,
)

///////////////////////////////////////////////////////////////////////////
// CONVERSION METHODS
///////////////////////////////////////////////////////////////////////////

internal fun UsersResponseDto.toUsersList(): List<User> {
    return data.items.map {
        User(
            id = it.id,
            name = it.name,
            firstName = it.firstName,
            lastName = it.lastName,
            jobTitle = it.jobTitle,
            pictureName = it.picture?.name,
            pictureUrl = it.picture?.href
        )
    }
}