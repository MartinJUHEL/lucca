package com.martin.lucca.feature.userdetails.domain

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.commonmodel.user.EmployeeDetails
import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.userdetails.presentation.EmployeeDetailsUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class BuildEmployeeDetailsUiStateTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var employeeService: EmployeeService
    private lateinit var useCase: BuildEmployeeDetailsUiState

    private val employeeId = 123
    private val employeeDetails = EmployeeDetails(
        id = employeeId,
        firstName = "John",
        lastName = "Doe",
        pictureName = "profile.jpg",
        pictureUrl = "http://example.com/profile.jpg",
        jobTitle = "Software Engineer",
        departmentName = "Engineering",
        departmentId = 1,
        legalEntityName = "Lucca",
        dtContractStart = LocalDateTime.now(),
        quote = "Working hard!",
        mail = "john.doe@lucca.fr",
        manager = Employee(
            id = 456,
            name = "Jane Smith",
            firstName = "Jane",
            lastName = "Smith",
            jobTitle = "Engineering Manager",
            pictureName = "manager.jpg",
            pictureUrl = "http://example.com/manager.jpg"
        ),
        directLine = "0123456789",
        birthDate = LocalDateTime.now()
    )

    ///////////////////////////////////////////////////////////////////////////
    // SETUP
    ///////////////////////////////////////////////////////////////////////////

    @Before
    fun setup() {
        employeeService = mockk()
        useCase = BuildEmployeeDetailsUiState(employeeService)
    }

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns Success when employee details are retrieved`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployeeDetails(any())
        } returns EmployeeService.EmployeeDetailsResult.Success(employeeDetails)

        // When
        val result = useCase(employeeId)

        // Then
        assertThat(result).isEqualTo(EmployeeDetailsUiState.Success(employeeDetails))
    }

    @Test
    fun `error case - returns Error when employee details cannot be retrieved`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployeeDetails(any())
        } returns EmployeeService.EmployeeDetailsResult.Error

        // When
        val result = useCase(employeeId)

        // Then
        assertThat(result).isEqualTo(EmployeeDetailsUiState.Error)
    }
} 