package com.martin.lucca.core.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A Base ViewModel which holds the state and methods to modify it.
 * As a convenience, a logger is also included.
 */
abstract class BaseViewModel<EVENT>() : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE DATA
    ///////////////////////////////////////////////////////////////////////////

    /** The dispatcher to use for IO. May be changed for testing. */
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    /** A channel to post events to. */
    private val eventChannel = Channel<EVENT>(Channel.BUFFERED)

    /** Flow to receive the events. */
    private val eventFlow = eventChannel.receiveAsFlow()


    ///////////////////////////////////////////////////////////////////////////
    // PROTECTED METHODS
    ///////////////////////////////////////////////////////////////////////////

    /** Sends an [event] to be intercepted by the UI. */
    protected fun sendEvent(event: EVENT) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    /** Collects events sent with [sendEvent]. */
    suspend fun collectEvent(onEvent: suspend (EVENT) -> Unit) {
        eventFlow.collect {
            onEvent(it)
        }
    }

    /**
     * Runs an suspended [action] in the scope of the ViewModel in the Main Thread.
     * Shouldn't be used to make network calls.
     */
    protected fun runCoroutine(action: suspend () -> Unit) {
        viewModelScope.launch {
            action()
        }
    }

    /**
     * Runs an suspended [action] in the scope of the ViewModel in the IO thread.
     * Then calls the [then] action on the main thread, with a possible parameter returned by the [action].
     */
    protected fun <RETURN> runCoroutineIO(
        action: suspend () -> RETURN,
        then: suspend (RETURN) -> Unit = { }
    ) {
        viewModelScope.launch {
            val value = withContext(ioDispatcher) {
                return@withContext action()
            }

            then(value)
        }
    }
}