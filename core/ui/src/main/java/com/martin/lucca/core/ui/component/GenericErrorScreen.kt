package com.martin.lucca.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.martin.lucca.core.ui.R
import com.martin.lucca.core.ui.theme.IconBig
import com.martin.lucca.core.ui.theme.MarginRegular
import com.martin.lucca.core.ui.theme.MarginSmall

@Composable
fun GenericErrorScreen(
    message: String = "Une erreur est survenue",
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MarginRegular),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MarginRegular)
        ) {
            Icon(
                imageVector = Icons.Rounded.Warning,
                contentDescription = "Error icon",
                modifier = Modifier.size(IconBig),
                tint = MaterialTheme.colorScheme.error
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onRetry,
                modifier = Modifier.padding(top = MarginSmall)
            ) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun GenericErrorScreenPreview() {
    MaterialTheme {
        GenericErrorScreen {}
    }
}