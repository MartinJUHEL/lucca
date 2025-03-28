package com.martin.lucca.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.martin.lucca.core.asyncimage.presentation.AuthenticatedAsyncImage
import com.martin.lucca.core.ui.R
import com.martin.lucca.core.ui.theme.MarginSmaller
import com.martin.lucca.core.ui.theme.Typography

private val IMAGE_SIZE = 40.dp

@Composable
fun EmployeeSmallTile(
    imageUrl: String?,
    name: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
    ) {

        imageUrl?.let {
            AuthenticatedAsyncImage(
                url = imageUrl,
                contentDescription = "Employee Picture",
                modifier = Modifier
                    .size(IMAGE_SIZE)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } ?: Image(
            modifier = Modifier
                .size(IMAGE_SIZE),
            painter = painterResource(R.drawable.ic_person_placeholder),
            contentDescription = "Employee Picture",
            contentScale = ContentScale.Fit,
        )

        Spacer(modifier = Modifier.width(MarginSmaller))

        Text(
            text = name,
            style = Typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun EmployeeSmallTilePreview() {
    EmployeeSmallTile(
        imageUrl = "https://example.com/profile.jpg",
        name = "Michel Constant"
    )
}