package com.martin.lucca.core.asyncimage.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.martin.lucca.core.network.BuildConfig

private const val AUTHORIZATION_HEADER = "Authorization"
private const val VALUE_HEADER_AUTHORIZATION_PREFIX = "Lucca application="

@Composable
fun AuthenticatedAsyncImage(
    url: String,
    placeholder: Painter? = null,
    error: Painter? = null,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .setHeader(
                AUTHORIZATION_HEADER,
                "${VALUE_HEADER_AUTHORIZATION_PREFIX}${BuildConfig.API_KEY}"
            )
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        error = error,
        placeholder = placeholder
    )
}