package com.martin.lucca.feature.userdetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.martin.lucca.core.asyncimage.presentation.AuthenticatedAsyncImage
import com.martin.lucca.core.common.locale.DATE_FORMAT_DD_MMMM_YYYY
import com.martin.lucca.core.common.locale.DATE_FORMAT_DMMM
import com.martin.lucca.core.common.locale.displayDate
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.commonmodel.user.EmployeeDetails
import com.martin.lucca.core.ui.component.CenteredCircularProgressIndicator
import com.martin.lucca.core.ui.component.ContactInfo
import com.martin.lucca.core.ui.component.EmployeeSmallTile
import com.martin.lucca.core.ui.component.GenericErrorScreen
import com.martin.lucca.core.ui.theme.BracingBlue
import com.martin.lucca.core.ui.theme.IconRegular
import com.martin.lucca.core.ui.theme.MarginPage
import com.martin.lucca.core.ui.theme.MarginRegular
import com.martin.lucca.core.ui.theme.MarginSmall
import com.martin.lucca.core.ui.theme.MarginSmaller
import com.martin.lucca.core.ui.theme.Typography
import java.time.LocalDateTime
import com.martin.lucca.core.ui.R as uiResources
import com.martin.lucca.feature.employeedetails.R as employeeDetailsResources

private val IMAGE_SIZE = 120.dp

@Composable
internal fun EmployeeDetailsScreen(
    uiState: EmployeeDetailsUiState,
    action: (EmployeeDetailsAction) -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                EmployeeDetailsUiState.Error -> GenericErrorScreen(onRetry = {
                    action(
                        EmployeeDetailsAction.Load
                    )
                })

                EmployeeDetailsUiState.Loading -> CenteredCircularProgressIndicator()
                is EmployeeDetailsUiState.Success -> EmployeeDetails(
                    uiState.employee,
                    onBackClicked = onBackClicked
                )
            }
        }
    }
}

@Composable
private fun EmployeeDetails(employee: EmployeeDetails, onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(onBackClicked, employee)
        Spacer(modifier = Modifier.height(MarginPage))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(employeeDetailsResources.string.manager),
            style = Typography.titleSmall
        )
        EmployeeSmallTile(
            modifier = Modifier.align(Alignment.Start),
            imageUrl = employee.manager.pictureUrl ?: "",
            name = employee.manager.fullName
        )
        Spacer(modifier = Modifier.height(MarginPage))
        ContactInfo(stringResource(employeeDetailsResources.string.mail_pro), employee.mail)
        Spacer(modifier = Modifier.height(MarginSmaller))
        employee.directLine?.let {
            ContactInfo(
                stringResource(employeeDetailsResources.string.direct_line),
                it
            )
        }
        Spacer(modifier = Modifier.height(MarginSmaller))
        ContactInfo(
            stringResource(employeeDetailsResources.string.birthday),
            stringResource(uiResources.string.on, employee.birthDate.displayDate(DATE_FORMAT_DMMM))
        )
        Spacer(modifier = Modifier.height(MarginPage))
    }
}

@Composable
private fun Header(
    onBackClicked: () -> Unit,
    employee: EmployeeDetails
) {
    Column(
        modifier = Modifier
            .background(color = BracingBlue)
            .padding(horizontal = MarginPage),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MarginPage))

        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopStart),
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back icon",
                    modifier = Modifier.size(IconRegular),
                    tint = Color.White
                )
            }
            employee.pictureUrl?.let {
                AuthenticatedAsyncImage(
                    modifier = Modifier
                        .size(IMAGE_SIZE)
                        .align(Alignment.Center),
                    url = it,
                    contentDescription = employee.pictureName,
                    contentScale = ContentScale.FillWidth,
                )
            } ?: Image(
                modifier = Modifier
                    .size(IMAGE_SIZE)
                    .align(Alignment.Center),
                painter = painterResource(uiResources.drawable.ic_person_placeholder),
                contentDescription = employee.pictureName,
                contentScale = ContentScale.Fit,
            )
        }

        Spacer(modifier = Modifier.height(MarginSmaller))

        Text(employee.fullName, style = Typography.titleMedium.copy(color = Color.White))
        Text(
            employee.legalEntityName,
            style = Typography.bodyLarge.copy(color = Color.White)
        )
        Text(
            stringResource(
                employeeDetailsResources.string.employee_since,
                employee.dtContractStart.displayDate(DATE_FORMAT_DD_MMMM_YYYY)
            ),
            style = Typography.bodyMedium.copy(
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(MarginSmall))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(MarginSmaller),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MarginRegular),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = employee.jobTitle,
                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = employee.departmentName, style = Typography.bodyMedium)
            }
        }
        Spacer(modifier = Modifier.height(MarginRegular))
    }
}

@Preview(showBackground = true)
@Composable
internal fun EmployeeDetailsScreenPreview() {
    EmployeeDetailsScreen(
        uiState = EmployeeDetailsUiState.Success(
            employee = EmployeeDetails(
                id = 1,
                firstName = "Augustin",
                lastName = "Bouvet",
                jobTitle = "Ingénieur R&D",
                departmentName = "Consulting",
                departmentId = 1,
                pictureName = "profile_picture.jpg",
                pictureUrl = null,
                legalEntityName = "LCC",
                dtContractStart = LocalDateTime.now(),
                quote = null,
                mail = "abouvet@example.org",
                manager = Employee(
                    id = 1,
                    name = "Marie Bragolet",
                    firstName = "Marie",
                    lastName = "Bragolet",
                    jobTitle = "Ingénieur R&D",
                    pictureName = "profile_picture.jpg",
                    pictureUrl = null
                ),
                directLine = "01 22 49 21 00",
                birthDate = LocalDateTime.now()
            )
        ),
        action = {},
        onBackClicked = {}
    )
}