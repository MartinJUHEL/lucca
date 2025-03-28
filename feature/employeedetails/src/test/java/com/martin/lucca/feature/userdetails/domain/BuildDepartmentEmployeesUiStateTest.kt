package com.martin.lucca.feature.userdetails.domain

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.feature.employee.data.dto.EmployeeByDepartmentRequest
import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.userdetails.presentation.DepartmentEmployeesUiState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BuildDepartmentEmployeesUiStateTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var employeeService: EmployeeService
    private lateinit var buildGetDepartmentEmployeesRequest: BuildGetDepartmentEmployeesRequest
    private lateinit var useCase: BuildDepartmentEmployeesUiState

    private val departmentId = 1
    private val employeeByDepartmentRequest = EmployeeByDepartmentRequest(
        departmentId = departmentId,
        fields = "id,firstName,lastName,picture[name,href],jobTitle,department[name,id],legalEntity[name],dtContractStart,quote,mail,manager[id,name,firstName,lastName,picture[id,name,href]],directLine,birthDate"
    )

    private val employees = listOf(
        Employee(
            id = 123,
            firstName = "John",
            lastName = "Doe",
            jobTitle = "Software Engineer",
            pictureName = "profile.jpg",
            pictureUrl = "http://example.com/profile.jpg"
        ),
        Employee(
            id = 456,
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
        buildGetDepartmentEmployeesRequest = mockk()
        useCase = BuildDepartmentEmployeesUiState(employeeService, buildGetDepartmentEmployeesRequest)

        every { buildGetDepartmentEmployeesRequest(departmentId) } returns employeeByDepartmentRequest
    }

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns Success with employees when service returns employees`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployeeByDepartment(employeeByDepartmentRequest)
        } returns EmployeeService.EmployeesResult.Success(employees)

        // When
        val result = useCase(departmentId)

        // Then
        assertThat(result).isEqualTo(DepartmentEmployeesUiState.Success(employees))
    }

    @Test
    fun `error case - returns Error when service returns error`() = runTest {
        // Given
        coEvery {
            employeeService.getEmployeeByDepartment(employeeByDepartmentRequest)
        } returns EmployeeService.EmployeesResult.Error

        // When
        val result = useCase(departmentId)

        // Then
        assertThat(result).isEqualTo(DepartmentEmployeesUiState.Error)
    }
} 