package com.martin.lucca.core.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object Thrombinoscope : Screen()

    @Serializable
    data class UserDetails(val userId: Int) : Screen()
}