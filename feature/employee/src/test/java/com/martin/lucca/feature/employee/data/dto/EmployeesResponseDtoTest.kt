package com.martin.lucca.feature.employee.data.dto

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import org.junit.Test

class EmployeesResponseDtoTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val employeesResponseDto = EmployeesResponseDto(
        data = DataResponseDto(
            items = listOf(
                EmployeeItemResponseDto(
                    id = 123,
                    firstName = "John",
                    lastName = "Doe",
                    jobTitle = "Software Engineer",
                    picture = PictureItemResponseDto(
                        name = "profile.jpg",
                        href = "http://example.com/profile.jpg"
                    )
                ),
                EmployeeItemResponseDto(
                    id = 456,
                    firstName = "Jane",
                    lastName = "Smith",
                    jobTitle = "Product Manager",
                    picture = null
                )
            )
        )
    )

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `toEmployeesList converts DTO to domain model correctly`() {
        // When
        val result = employeesResponseDto.toEmployeesList()

        // Then
        assertThat(result).hasSize(2)

        // Premier employé
        val firstEmployee = result[0]
        assertThat(firstEmployee.id).isEqualTo(123)
        assertThat(firstEmployee.firstName).isEqualTo("John")
        assertThat(firstEmployee.lastName).isEqualTo("Doe")
        assertThat(firstEmployee.jobTitle).isEqualTo("Software Engineer")
        assertThat(firstEmployee.pictureName).isEqualTo("profile.jpg")
        assertThat(firstEmployee.pictureUrl).isEqualTo("http://example.com/profile.jpg")

        // Deuxième employé (avec picture null)
        val secondEmployee = result[1]
        assertThat(secondEmployee.id).isEqualTo(456)
        assertThat(secondEmployee.firstName).isEqualTo("Jane")
        assertThat(secondEmployee.lastName).isEqualTo("Smith")
        assertThat(secondEmployee.jobTitle).isEqualTo("Product Manager")
        assertThat(secondEmployee.pictureName).isNull()
        assertThat(secondEmployee.pictureUrl).isNull()
    }

    @Test
    fun `toEmployeesList handles empty list correctly`() {
        // Given
        val emptyDto = EmployeesResponseDto(
            data = DataResponseDto(
                items = emptyList()
            )
        )

        // When
        val result = emptyDto.toEmployeesList()

        // Then
        assertThat(result).isEmpty()
    }
} 