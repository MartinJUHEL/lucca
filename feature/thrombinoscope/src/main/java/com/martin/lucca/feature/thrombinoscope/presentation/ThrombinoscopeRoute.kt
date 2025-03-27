package com.martin.lucca.feature.thrombinoscope.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.martin.lucca.core.navigation.Screen

@Composable
fun ThrombinoscopeRoute(navController: NavController) {
    val viewModel: ThrombinoscopeViewModel = hiltViewModel()
    val uiState by viewModel.thrombinoscopeUiState.collectAsStateWithLifecycle()

    ThrombinoscopeScreen(
        uiState = uiState,
        action = viewModel::onAction,
    )

    LaunchedEffect(Unit) {
        viewModel.onAction(ThrombinoscopeAction.Load)
    }

    // Navigation.
    LaunchedEffect(Unit) {
        viewModel.collectEvent { event ->
            when (event) {
                is ThrombinoscopeViewModel.Event.GoToUserDetails -> navController.navigate(
                    Screen.EmployeeDetails(
                        event.userId
                    )
                )
            }
        }
    }
}