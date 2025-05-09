package com.martin.lucca.feature.thrombinoscope.presentation

import com.martin.lucca.core.common.viewmodel.BaseViewModel
import com.martin.lucca.feature.thrombinoscope.domain.usecase.BuildThrombinoscopeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ThrombinoscopeViewModel @Inject constructor(
    private val buildThrombinoscopeUiState: BuildThrombinoscopeUiState
) : BaseViewModel<ThrombinoscopeViewModel.Event>() {

    private val _thrombinoscopeUiState =
        MutableStateFlow<ThrombinoscopeUiState>(ThrombinoscopeUiState.Loading)
    val thrombinoscopeUiState = _thrombinoscopeUiState.asStateFlow()

    ///////////////////////////////////////////////////////////////////////////
    // EVENTS
    ///////////////////////////////////////////////////////////////////////////

    sealed interface Event {
        data class GoToUserDetails(val userId: Int) : Event
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////

    fun onAction(action: ThrombinoscopeAction) {
        when (action) {
            is ThrombinoscopeAction.Load -> load()
            is ThrombinoscopeAction.OnUserClicked -> sendEvent(Event.GoToUserDetails(action.userId))
            ThrombinoscopeAction.OnPullToRefresh -> onPullToRefresh()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////

    private fun load() {
        runCoroutineIO(
            action = {
                buildThrombinoscopeUiState.invoke()
            },
            then = { uiState ->
                _thrombinoscopeUiState.value = uiState
            }
        )
    }

    private fun onPullToRefresh() {
        val currentState = _thrombinoscopeUiState.value
        if (currentState is ThrombinoscopeUiState.Success) {
            _thrombinoscopeUiState.value = currentState.copy(isRefreshing = true)
            load()
        }
    }
}