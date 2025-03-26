package com.martin.lucca.core.commonmodel.user

data class User(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String,
    val jobTitle: String?,
    val pictureName: String?,
    val pictureUrl: String?,
)