package com.martin.lucca.core.commonmodel.user

data class Employee(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val jobTitle: String?,
    val pictureName: String?,
    val pictureUrl: String?,
){
    val fullName = "$firstName $lastName"
}