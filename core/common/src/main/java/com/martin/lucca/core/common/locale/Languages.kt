package com.martin.lucca.core.common.locale

import java.util.Locale

/** The locales that are supported by the application. */
private val supportedLocales = listOf(
    Locale.UK,         // Preferred/default is first.
    Locale.FRANCE,
)

/**
 * @return either the Locale of the device, or the fallback if the app doesn't manage it.
 * This is useful as a replacement to Locale.getDefault(), which can return a Locale that is not supported
 * by the app. Displaying a Date with such Locale will display a different language than the one the app uses!
 * @param currentLocale shouldn't be used, used only for test to override it.
 */
fun getSupportedLocale(currentLocale: Locale = Locale.getDefault()): Locale {
    // Finds the current among the authorized one. If not found, fallback to the preferred.
    val foundLocale = supportedLocales.firstOrNull { locale ->
        locale.language == currentLocale.language
    }

    return foundLocale ?: supportedLocales[0]
}