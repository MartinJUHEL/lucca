package com.martin.lucca.feature.thrombinoscope.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.martin.lucca.core.asyncimage.presentation.AuthenticatedAsyncImage
import com.martin.lucca.core.commonmodel.user.Employee
import com.martin.lucca.core.ui.R
import com.martin.lucca.core.ui.theme.MarginSmall
import com.martin.lucca.core.ui.theme.MarginSmaller
import com.martin.lucca.core.ui.theme.MarginTiny
import com.martin.lucca.core.ui.theme.Typography

private val IMAGE_SIZE = 120.dp
private val CARD_HEIGHT = 200.dp

@Composable
internal fun EmployeeCard(
    employee: Employee,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(CARD_HEIGHT)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = MarginSmall),
        shape = RoundedCornerShape(MarginTiny),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            employee.pictureUrl?.let {
                AuthenticatedAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IMAGE_SIZE),
                    url = it,
                    contentDescription = employee.pictureName,
                    contentScale = ContentScale.FillWidth,
                )
            } ?: Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IMAGE_SIZE),
                painter = painterResource(R.drawable.ic_person_placeholder),
                contentDescription = employee.pictureName,
                contentScale = ContentScale.Fit,
            )


            Spacer(modifier = Modifier.padding(MarginSmaller))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MarginSmall)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(),
                    text = employee.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )

                employee.jobTitle?.let {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = Typography.bodySmall.copy(color = MaterialTheme.colorScheme.outline)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun UserCardPreview() {
    MaterialTheme {
        EmployeeCard(
            employee = Employee(
                id = 1,
                name = "Martin Juhel",
                firstName = "Martin",
                lastName = "Juhel",
                jobTitle = "Android Developer",
                pictureName = "profile_picture.jpg",
                pictureUrl = null
            ),
        )
    }
}