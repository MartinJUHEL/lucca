package com.martin.lucca.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.martin.lucca.core.ui.theme.MarginTiny
import com.martin.lucca.core.ui.theme.Typography

@Composable
fun ContactInfo(label: String, value: String, onClicked: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MarginTiny)
            .clickable { onClicked() }
    ) {
        Text(label, style = Typography.titleSmall)
        Spacer(modifier = Modifier.padding(MarginTiny))
        Text(value, style = Typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary))
    }
}

@Preview(showBackground = true)
@Composable
internal fun ContactInfoPreview() {
    MaterialTheme {
        ContactInfo(label = "Télephone", value = "00 00 00 00 00")
    }
}