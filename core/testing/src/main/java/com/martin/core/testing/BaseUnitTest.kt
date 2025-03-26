package com.martin.core.testing

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

abstract class BaseUnitTest {

    ///////////////////////////////////////////////////////////////////////////
    // APPLIED RULES
    ///////////////////////////////////////////////////////////////////////////

    // To prevent "Module with the Main dispatcher had failed to initialize" error.
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Useful if using Looper.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    ///////////////////////////////////////////////////////////////////////////
    // LIFE CYCLE
    ///////////////////////////////////////////////////////////////////////////

    @Before
    @CallSuper
    open fun beforeEachTest() {
        MockKAnnotations.init(this)
    }

    @After
    open fun afterEachTest() {
        clearAllMocks()     // Clears all mocks (all "every {}").
        unmockkAll()        // Unmocks all mockkStatic.
    }

    ///////////////////////////////////////////////////////////////////////////
    // RULES
    ///////////////////////////////////////////////////////////////////////////

    /**
     * In case of error: "Module with the Main dispatcher had failed to initialize."
     * https://developer.android.com/kotlin/coroutines/test#setting-main-dispatcher
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    class MainDispatcherRule(
        private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description?) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description?) {
            Dispatchers.resetMain()
        }
    }
}
