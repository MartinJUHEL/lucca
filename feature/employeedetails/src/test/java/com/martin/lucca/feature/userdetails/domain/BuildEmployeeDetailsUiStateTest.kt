package com.martin.lucca.feature.userdetails.domain

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.commonmodel.user.EmployeeDetails
import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsRequest
import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.userdetails.presentation.EmployeeDetailsUiState
import io.mockk.coEvery
import io.mockk.every
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
    private lateinit var buildGetEmployeeDetailsRequest: BuildGetEmployeeDetailsRequest
    private lateinit var useCase: BuildEmployeeDetailsUiState

    private val employeeId = 123
    private val employeeDetailsRequest = EmployeeDetailsRequest(
        employeeId = employeeId,
        fields = "id,firstName,lastName,picture[name,href],jobTitle,department[name,id],legalEntity[name],dtContractStart,quote,mail,manager[id,name,firstName,lastName,picture[id,name,href]],directLine,birthDate"
    )
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
        buildGetEmployeeDetailsRequest = mockk()
        useCase = BuildEmployeeDetailsUiState(employeeService, buildGetEmployeeDetailsRequest)

        every { buildGetEmployeeDetailsRequest(employeeId) } returns employeeDetailsRequest
    }

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns Success when employee details are retrieved`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployeeDetails(employeeDetailsRequest)
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
            employeeService.getEmployeeDetails(employeeDetailsRequest)
        } returns EmployeeService.EmployeeDetailsResult.Error

        // When
        val result = useCase(employeeId)

        // Then
        assertThat(result).isEqualTo(EmployeeDetailsUiState.Error)
    }
} 