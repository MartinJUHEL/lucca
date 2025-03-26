package com.martin.lucca.feature.thrombinoscope.domain.usecase

import com.martin.lucca.feature.user.data.dto.UsersRequest
import javax.inject.Inject

private const val APP_INSTANCE_ID = 15
private const val OPERATIONS = 1
private const val FIELDS = "id,name,firstName,lastName,mail,jobTitle,picture[name,href]"
private const val ORDER_BY = "lastName,asc"

class BuildGetUsersRequest @Inject constructor() {

    operator fun invoke(): UsersRequest {
        return UsersRequest(
            appInstanceId = APP_INSTANCE_ID,
            operations = OPERATIONS,
            fields = FIELDS,
            orderBy = ORDER_BY
        )
    }
}