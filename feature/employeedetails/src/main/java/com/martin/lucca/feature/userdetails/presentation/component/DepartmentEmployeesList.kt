package com.martin.lucca.feature.userdetails.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.martin.lucca.core.ui.component.EmployeeSmallTile
import com.martin.lucca.core.ui.theme.MarginRegular
import com.martin.lucca.core.ui.theme.MarginSmaller
import com.martin.lucca.core.ui.theme.Typography
import com.martin.lucca.feature.employeedetails.R
import com.martin.lucca.feature.userdetails.presentation.DepartmentEmployeesUiState

@Composable
internal fun DepartmentEmployeesList(
    departmentEmployeesUiState: DepartmentEmployeesUiState,
    onEmployeeClicked: (Int) -> Unit
) {
    return when (departmentEmployeesUiState) {
        DepartmentEmployeesUiState.Loading, DepartmentEmployeesUiState.Error -> Unit
        is DepartmentEmployeesUiState.Success -> {
            Column {
                Spacer(modifier = Modifier.height(MarginRegular))
                Text(
                    text = stringResource(R.string.department_members),
                    style = Typography.titleSmall
                )
                Spacer(modifier = Modifier.height(MarginSmaller))
                Column {
                    departmentEmployeesUiState.employees.forEach { employee ->
                        EmployeeSmallTile(
                            modifier = Modifier.padding(MarginSmaller),
                            name = employee.fullName,
                            imageUrl = employee.pictureUrl,
                            onClick = {
                                onEmployeeClicked(employee.id)
                            })
                    }
                }
            }
        }
    }
}