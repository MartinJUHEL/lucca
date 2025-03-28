package com.martin.lucca.feature.thrombinoscope.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.thrombinoscope.presentation.ThrombinoscopeUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BuildThrombinoscopeUiStateTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var employeeService: EmployeeService
    private lateinit var useCase: BuildThrombinoscopeUiState

    private val employees = listOf(
        Employee(
            id = 123,
            name = "John Doe",
            firstName = "John",
            lastName = "Doe",
            jobTitle = "Software Engineer",
            pictureName = "profile.jpg",
            pictureUrl = "http://example.com/profile.jpg"
        ),
        Employee(
            id = 456,
            name = "Jane Smith",
            firstName = "Jane",
            lastName = "Smith",
            jobTitle = "Product Manager",
            pictureName = null,
            pictureUrl = null
        )
    )

    ///////////////////////////////////////////////////////////////////////////
    // SETUP
    ///////////////////////////////////////////////////////////////////////////

    @Before
    fun setup() {
        employeeService = mockk()
        useCase = BuildThrombinoscopeUiState(employeeService)
    }

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns Success with employees when service returns employees`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployees(any())
        } returns EmployeeService.EmployeesResult.Success(employees)

        // When
        val result = useCase()

        // Then
        assertThat(result).isEqualTo(ThrombinoscopeUiState.Success(employees))
    }

    @Test
    fun `empty case - returns Empty when service returns empty list`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployees(any())
        } returns EmployeeService.EmployeesResult.Success(emptyList())

        // When
        val result = useCase()

        // Then
        assertThat(result).isEqualTo(ThrombinoscopeUiState.Empty)
    }

    @Test
    fun `error case - returns Error when service returns error`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployees(any())
        } returns EmployeeService.EmployeesResult.Error

        // When
        val result = useCase()

        // Then
        assertThat(result).isEqualTo(ThrombinoscopeUiState.Error)
    }
} 