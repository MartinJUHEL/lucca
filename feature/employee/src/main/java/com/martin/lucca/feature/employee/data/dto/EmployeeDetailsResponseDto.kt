package com.martin.lucca.feature.employee.data.dto

import com.martin.lucca.core.common.locale.DATE_FORMAT_YMDTMS
import com.martin.lucca.core.common.locale.toLocalDateTime
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.commonmodel.user.EmployeeDetails
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeDetailsResponseDto(
    val data: EmployeeDataDto,
)

@JsonClass(generateAdapter = true)
data class EmployeeDataDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val picture: PictureDto,
    val jobTitle: String,
    val department: DepartmentDto,
    val legalEntity: LegalEntityDto,
    val dtContractStart: String,
    val quote: String?,
    val mail: String,
    val manager: ManagerDto,
    val directLine: String?,
    val birthDate: String
)

@JsonClass(generateAdapter = true)
data class PictureDto(
    val name: String?,
    val href: String
)

@JsonClass(generateAdapter = true)
data class DepartmentDto(
    val name: String,
    val id: Int
)

@JsonClass(generateAdapter = true)
data class LegalEntityDto(
    val name: String
)

@JsonClass(generateAdapter = true)
data class ManagerDto(
    val id: Long,
    val name: String,
    val firstName: String,
    val lastName: String,
    val picture: PictureDto
)

///////////////////////////////////////////////////////////////////////////
// CONVERSION METHODS
///////////////////////////////////////////////////////////////////////////

internal fun EmployeeDetailsResponseDto.toEmployeeDetails(): EmployeeDetails {
    return EmployeeDetails(
        id = data.id,
        firstName = data.firstName,
        lastName = data.lastName,
        pictureName = data.picture.name,
        pictureUrl = data.picture.href,
        jobTitle = data.jobTitle,
        departmentName = data.department.name,
        departmentId = data.department.id,
        legalEntityName = data.legalEntity.name,
        dtContractStart = data.dtContractStart.toLocalDateTime(DATE_FORMAT_YMDTMS),
        quote = data.quote,
        mail = data.mail,
        manager = Employee(
            id = data.manager.id.toInt(),
            firstName = data.manager.firstName,
            lastName = data.manager.lastName,
            jobTitle = null,
            pictureName = data.manager.picture.name,
            pictureUrl = data.manager.picture.href
        ),
        directLine = data.directLine,
        birthDate = data.birthDate.toLocalDateTime(DATE_FORMAT_YMDTMS)
    )
}

