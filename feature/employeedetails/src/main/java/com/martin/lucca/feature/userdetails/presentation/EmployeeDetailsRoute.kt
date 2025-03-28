package com.martin.lucca.feature.userdetails.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.martin.lucca.core.navigation.Screen

@Composable
fun EmployeeDetailsRoute(navController: NavController) {
    val viewModel: EmployeeDetailsViewModel = hiltViewModel()
    val uiState by viewModel.employeeDetailsUiState.collectAsStateWithLifecycle()

    EmployeeDetailsScreen(
        uiState = uiState,
        action = viewModel::onAction,
        onBackClicked = navController::popBackStack
    )

    LaunchedEffect(Unit) {
        viewModel.onAction(EmployeeDetailsAction.Load)
    }

    // Navigation.
    LaunchedEffect(Unit) {
        viewModel.collectEvent { event ->
            when (event) {
                is EmployeeDetailsViewModel.Event.GoToEmployeeDetails -> {
                    navController.navigate(Screen.EmployeeDetails(event.employeeId))
                }
            }
        }
    }
}