package com.martin.lucca.feature.thrombinoscope.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import com.martin.lucca.feature.employee.data.dto.EmployeesRequest
import org.junit.Test

class BuildGetEmployeesRequestTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val useCase = BuildGetEmployeesRequest()

    private val expectedAppInstanceId = 15
    private val expectedOperations = 1
    private val expectedFields = "id,name,firstName,lastName,mail,jobTitle,picture[name,href]"
    private val expectedOrderBy = "lastName,asc"

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `nominal case - returns request with correct predefined values`() {
        // When
        val result = useCase()

        // Then
        assertThat(result).isEqualTo(
            EmployeesRequest(
                appInstanceId = expectedAppInstanceId,
                operations = expectedOperations,
                fields = expectedFields,
                orderBy = expectedOrderBy
            )
        )
    }
} 