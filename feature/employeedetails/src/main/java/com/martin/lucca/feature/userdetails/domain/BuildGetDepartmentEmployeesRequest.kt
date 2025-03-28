package com.martin.lucca.feature.userdetails.domain

import com.martin.lucca.feature.employee.data.dto.EmployeeByDepartmentRequest
import javax.inject.Inject

private const val FIELDS =
    "id,firstName,lastName,picture[name,href],jobTitle,department[name,id],legalEntity[name],dtContractStart,quote,mail,manager[id,name,firstName,lastName,picture[id,name,href]],directLine,birthDate"

internal class BuildGetDepartmentEmployeesRequest @Inject constructor() {
    operator fun invoke(departmentId: Int): EmployeeByDepartmentRequest {
        return EmployeeByDepartmentRequest(
            departmentId = departmentId,
            fields = FIELDS,
        )
    }
}