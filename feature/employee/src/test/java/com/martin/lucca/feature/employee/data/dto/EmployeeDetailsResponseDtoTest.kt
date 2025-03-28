package com.martin.lucca.feature.employee.data.dto

import com.google.common.truth.Truth.assertThat
import com.martin.core.testing.BaseUnitTest
import org.junit.Test

class EmployeeDetailsResponseDtoTest : BaseUnitTest() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val employeeDetailsResponseDto = EmployeeDetailsResponseDto(
        data = EmployeeDataDto(
            id = 123,
            firstName = "John",
            lastName = "Doe",
            picture = PictureDto(
                name = "profile.jpg",
                href = "http://example.com/profile.jpg"
            ),
            jobTitle = "Software Engineer",
            department = DepartmentDto(
                name = "Engineering",
                id = 1
            ),
            legalEntity = LegalEntityDto(
                name = "Lucca"
            ),
            dtContractStart = "2024-03-15T09:00:00",
            quote = "Working hard!",
            mail = "john.doe@lucca.fr",
            manager = ManagerDto(
                id = 456L,
                name = "Jane Smith",
                firstName = "Jane",
                lastName = "Smith",
                picture = PictureDto(
                    name = "manager.jpg",
                    href = "http://example.com/manager.jpg"
                )
            ),
            directLine = "0123456789",
            birthDate = "1990-01-01T00:00:00"
        )
    )

    ///////////////////////////////////////////////////////////////////////////
    // TESTS
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `toEmployeeDetails converts DTO to domain model correctly`() {
        // When
        val result = employeeDetailsResponseDto.toEmployeeDetails()

        // Then
        assertThat(result.id).isEqualTo(123)
        assertThat(result.firstName).isEqualTo("John")
        assertThat(result.lastName).isEqualTo("Doe")
        assertThat(result.pictureName).isEqualTo("profile.jpg")
        assertThat(result.pictureUrl).isEqualTo("http://example.com/profile.jpg")
        assertThat(result.jobTitle).isEqualTo("Software Engineer")
        assertThat(result.departmentName).isEqualTo("Engineering")
        assertThat(result.departmentId).isEqualTo(1)
        assertThat(result.legalEntityName).isEqualTo("Lucca")
        assertThat(result.quote).isEqualTo("Working hard!")
        assertThat(result.mail).isEqualTo("john.doe@lucca.fr")
        assertThat(result.directLine).isEqualTo("0123456789")
        
        // Vérification du manager
        assertThat(result.manager.id).isEqualTo(456)
        assertThat(result.manager.firstName).isEqualTo("Jane")
        assertThat(result.manager.lastName).isEqualTo("Smith")
        assertThat(result.manager.jobTitle).isNull()
        assertThat(result.manager.pictureName).isEqualTo("manager.jpg")
        assertThat(result.manager.pictureUrl).isEqualTo("http://example.com/manager.jpg")

        // Vérification des dates
        assertThat(result.dtContractStart.toString()).isEqualTo("2024-03-15T09:00")
        assertThat(result.birthDate.toString()).isEqualTo("1990-01-01T00:00")
    }

    @Test
    fun `toEmployeeDetails handles null values correctly`() {
        // Given
        val dtoWithNulls = EmployeeDetailsResponseDto(
            data = EmployeeDataDto(
                id = 123,
                firstName = "John",
                lastName = "Doe",
                picture = PictureDto(
                    name = null,
                    href = "http://example.com/profile.jpg"
                ),
                jobTitle = "Software Engineer",
                department = DepartmentDto(
                    name = "Engineering",
                    id = 1
                ),
                legalEntity = LegalEntityDto(
                    name = "Lucca"
                ),
                dtContractStart = "2024-03-15T09:00:00",
                quote = null,
                mail = "john.doe@lucca.fr",
                manager = ManagerDto(
                    id = 456L,
                    name = "Jane Smith",
                    firstName = "Jane",
                    lastName = "Smith",
                    picture = PictureDto(
                        name = null,
                        href = "http://example.com/manager.jpg"
                    )
                ),
                directLine = null,
                birthDate = "1990-01-01T00:00:00"
            )
        )

        // When
        val result = dtoWithNulls.toEmployeeDetails()

        // Then
        assertThat(result.pictureName).isNull()
        assertThat(result.quote).isNull()
        assertThat(result.directLine).isNull()
        assertThat(result.manager.pictureName).isNull()
    }
} 