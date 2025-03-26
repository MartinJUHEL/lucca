package com.martin.lucca.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyMedium = TextStyle(
        color = DefaultTextColor,
        fontFamily = defaultFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeNormal,
        lineHeight = 18.sp,
        letterSpacing = 0.2.sp
    ),
    bodySmall = TextStyle(
        color = DefaultTextColor,
        fontFamily = defaultFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeSmall,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp
    ),
    titleLarge = TextStyle(
        color = DefaultTextColor,
        fontFamily = redHatFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 38.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        color = DefaultTextColor,
        fontFamily = redHatFamily,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeBig,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        color = DefaultTextColor,
        fontFamily = redHatFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        color = DefaultTextColor,
        fontFamily = defaultFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeTiny,
        lineHeight = FontSizeTiny,
        letterSpacing = 0.5.sp
    ),
)