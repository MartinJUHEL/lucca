package com.martin.lucca.feature.employee.data.service

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.core.common.network.ErrorInfo
import com.martin.lucca.core.common.network.FetchedResponse
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.commonmodel.user.EmployeeDetails
import com.martin.lucca.feature.employee.data.api.EmployeeApi
import com.martin.lucca.feature.employee.data.dto.EmployeeByDepartmentRequest
import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsRequest
import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsResponseDto
import com.martin.lucca.feature.employee.data.dto.EmployeesRequest
import com.martin.lucca.feature.employee.data.dto.EmployeesResponseDto
import com.martin.network.SafeHttpCaller
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class EmployeeServiceRemoteTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var employeeApi: EmployeeApi
    private lateinit var safeHttpCaller: SafeHttpCaller
    private lateinit var service: EmployeeServiceRemote

    private val employeesRequest = EmployeesRequest(
        appInstanceId = 15,
        operations = 1,
        fields = "id,name",
        orderBy = "lastName,asc"
    )

    private val employeeDetailsRequest = EmployeeDetailsRequest(
        employeeId = 123,
        fields = "id,firstName,lastName"
    )

    private val employeeByDepartmentRequest = EmployeeByDepartmentRequest(
        departmentId = 1,
        fields = "id,firstName,lastName"
    )

    private val employee = Employee(
        id = 123,
        firstName = "John",
        lastName = "Doe",
        jobTitle = "Software Engineer",
        pictureName = "profile.jpg",
        pictureUrl = "http://example.com/profile.jpg"
    )

    private val employeeDetails = EmployeeDetails(
        id = 123,
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
        employeeApi = mockk(relaxed = true)
        safeHttpCaller = mockk()
        service = EmployeeServiceRemote(employeeApi, safeHttpCaller)
    }

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `getEmployees - returns Success when API call succeeds`() = runTest {
        // Given
        coEvery {
            safeHttpCaller.call<EmployeesResponseDto, List<Employee>>(
                action = any(),
                transform = any()
            )
        } returns FetchedResponse.Success(listOf(employee))

        // When
        val result = service.getEmployees(employeesRequest)

        // Then
        assertThat(result).isEqualTo(EmployeeService.EmployeesResult.Success(listOf(employee)))
    }

    @Test
    fun `getEmployees - returns Error when API call fails`() = runTest {
        // Given
        coEvery {
            safeHttpCaller.call<EmployeesResponseDto, List<Employee>>(
                action = any(),
                transform = any()
            )
        } returns FetchedResponse.Error(error = ErrorInfo(httpCode = 1, message = "error"))

        // When
        val result = service.getEmployees(employeesRequest)

        // Then
        assertThat(result).isEqualTo(EmployeeService.EmployeesResult.Error)
    }

    @Test
    fun `getEmployeeDetails - returns Success when API call succeeds`() = runTest {
        // Given
        coEvery {
            safeHttpCaller.call<EmployeeDetailsResponseDto, EmployeeDetails>(
                action = any(),
                transform = any()
            )
        } returns FetchedResponse.Success(employeeDetails)

        // When
        val result = service.getEmployeeDetails(employeeDetailsRequest)

        // Then
        assertThat(result).isEqualTo(EmployeeService.EmployeeDetailsResult.Success(employeeDetails))
    }

    @Test
    fun `getEmployeeDetails - returns Error when API call fails`() = runTest {
        // Given
        coEvery {
            safeHttpCaller.call<EmployeeDetailsResponseDto, EmployeeDetails>(
                action = any(),
                transform = any()
            )
        } returns FetchedResponse.Error(error = ErrorInfo(httpCode = 1, message = "error"))

        // When
        val result = service.getEmployeeDetails(employeeDetailsRequest)

        // Then
        assertThat(result).isEqualTo(EmployeeService.EmployeeDetailsResult.Error)
    }

    @Test
    fun `getEmployeeByDepartment - returns Success when API call succeeds`() = runTest {
        // Given
        coEvery {
            safeHttpCaller.call<EmployeesResponseDto, List<Employee>>(
                action = any(),
                transform = any()
            )
        } returns FetchedResponse.Success(listOf(employee))

        // When
        val result = service.getEmployeeByDepartment(employeeByDepartmentRequest)

        // Then
        assertThat(result).isEqualTo(EmployeeService.EmployeesResult.Success(listOf(employee)))
    }

    @Test
    fun `getEmployeeByDepartment - returns Error when API call fails`() = runTest {
        // Given
        coEvery {
            safeHttpCaller.call<EmployeesResponseDto, List<Employee>>(
                action = any(),
                transform = any()
            )
        } returns FetchedResponse.Error(error = ErrorInfo(httpCode = 1, message = "error"))

        // When
        val result = service.getEmployeeByDepartment(employeeByDepartmentRequest)

        // Then
        assertThat(result).isEqualTo(EmployeeService.EmployeesResult.Error)
    }
} 