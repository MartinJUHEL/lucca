package com.martin.lucca.feature.employee.data.dto

import com.martin.lucca.core.commonmodel.user.Employee
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeesResponseDto(
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

internal fun EmployeesResponseDto.toUsersList(): List<Employee> {
    return data.items.map {
        Employee(
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