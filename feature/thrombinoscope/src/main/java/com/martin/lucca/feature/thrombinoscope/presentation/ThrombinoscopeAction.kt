package com.martin.lucca.feature.thrombinoscope.presentation

internal sealed interface ThrombinoscopeAction {
    data object Load : ThrombinoscopeAction
    data object OnPullToRefresh : ThrombinoscopeAction
    data class OnUserClicked(val userId: Int) : ThrombinoscopeAction
}