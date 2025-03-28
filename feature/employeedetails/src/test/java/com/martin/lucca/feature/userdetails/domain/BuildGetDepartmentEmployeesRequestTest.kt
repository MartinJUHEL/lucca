package com.martin.lucca.feature.userdetails.domain

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.feature.employee.data.dto.EmployeeByDepartmentRequest
import org.junit.Test

class BuildGetDepartmentEmployeesRequestTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val useCase = BuildGetDepartmentEmployeesRequest()

    private val departmentId = 1
    private val expectedFields = "id,firstName,lastName,picture[name,href],jobTitle,department[name,id],legalEntity[name],dtContractStart,quote,mail,manager[id,name,firstName,lastName,picture[id,name,href]],directLine,birthDate"

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns request with correct department id and fields`() {
        // When
        val result = useCase(departmentId)

        // Then
        assertThat(result).isEqualTo(
            EmployeeByDepartmentRequest(
                departmentId = departmentId,
                fields = expectedFields
            )
        )
    }
} 