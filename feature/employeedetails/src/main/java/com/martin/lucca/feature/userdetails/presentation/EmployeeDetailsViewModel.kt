package com.martin.lucca.feature.userdetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.martin.lucca.core.common.viewmodel.BaseViewModel
import com.martin.lucca.core.navigation.Screen
import com.martin.lucca.feature.userdetails.domain.BuildEmployeeDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class EmployeeDetailsViewModel @Inject constructor(
    private val buildEmployeeDetailsUiState: BuildEmployeeDetailsUiState,
    private val getEmployeeId: GetEmployeeId
) :
    BaseViewModel<EmployeeDetailsViewModel.Event>() {

    private val _employeeDetailsUiState =
        MutableStateFlow<EmployeeDetailsUiState>(EmployeeDetailsUiState.Loading)
    val employeeDetailsUiState = _employeeDetailsUiState.asStateFlow()

    ///////////////////////////////////////////////////////////////////////////
    // EVENTS
    ///////////////////////////////////////////////////////////////////////////

    sealed interface Event {

    }

    ///////////////////////////////////////////////////////////////////////////
    // SAVED STATE HANDLE
    ///////////////////////////////////////////////////////////////////////////

    /** Having a class helps testing. */
    data class GetEmployeeId @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
    ) {
        operator fun invoke() = savedStateHandle.toRoute<Screen.EmployeeDetails>().employeeId
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////

    fun onAction(action: EmployeeDetailsAction) {
        when (action) {
            is EmployeeDetailsAction.Load -> load()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////

    private fun load() {
        val employeeId = getEmployeeId()

        _employeeDetailsUiState.value = EmployeeDetailsUiState.Loading
        runCoroutineIO(
            action = {
                buildEmployeeDetailsUiState(employeeId)
            },
            then = { uiState ->
                _employeeDetailsUiState.value = uiState
            }
        )
    }
}