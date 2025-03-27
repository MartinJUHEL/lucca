package com.martin.lucca.feature.userdetails.domain

import com.martin.lucca.feature.employee.data.dto.EmployeeDetailsRequest
import javax.inject.Inject

private const val FIELDS =
    "id,firstName,lastName,picture[name,href],jobTitle,department[name,id],legalEntity[name],dtContractStart,quote,mail,manager[id,name,firstName,lastName,picture[id,name,href]],directLine,birthDate"

class BuildGetEmployeeDetailsRequest @Inject constructor() {
    operator fun invoke(employeeId: Int): EmployeeDetailsRequest {
        return EmployeeDetailsRequest(
            employeeId = employeeId,
            fields = FIELDS,
        )
    }
}