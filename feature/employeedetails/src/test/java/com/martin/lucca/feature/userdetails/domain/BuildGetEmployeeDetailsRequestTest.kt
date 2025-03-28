package com.martin.lucca.feature.userdetails.domain

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsRequest
import org.junit.Test

class BuildGetEmployeeDetailsRequestTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val useCase = BuildGetEmployeeDetailsRequest()
    private val employeeId = 123

    private val expectedFields = "id,firstName,lastName,picture[name,href],jobTitle,department[name,id],legalEntity[name],dtContractStart,quote,mail,manager[id,name,firstName,lastName,picture[id,name,href]],directLine,birthDate"

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns request with correct employee id and fields`() {
        // When
        val result = useCase(employeeId)

        // Then
        assertThat(result).isEqualTo(
            EmployeeDetailsRequest(
                employeeId = employeeId,
                fields = expectedFields
            )
        )
    }
} 