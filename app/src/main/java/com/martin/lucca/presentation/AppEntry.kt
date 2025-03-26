package com.martin.lucca.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.lucca.core.navigation.Screen
import com.martin.lucca.feature.thrombinoscope.presentation.ThrombinoscopeRoute

@Composable
fun AppEntry(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Thrombinoscope,
    ) {
        composable<Screen.Thrombinoscope> {
            ThrombinoscopeRoute(navController = navController)
        }
        composable<Screen.UserDetails> {
            ThrombinoscopeRoute(navController = navController)
        }
    }
}