package com.martin.lucca.core.common.locale

import com.google.common.truth.Truth.assertThat
import com.martin.lucca.core.common.locale.getSupportedLocale
import org.junit.Test
import java.util.Locale

class LanguagesKtTest {

    @Test
    fun `getValidLanguages, supported languages`() {
        assertThat(getSupportedLocale(Locale.UK)).isEqualTo(Locale.UK)
        assertThat(getSupportedLocale(Locale.ENGLISH)).isEqualTo(Locale.UK)
        assertThat(getSupportedLocale(Locale.US)).isEqualTo(Locale.UK)

        assertThat(getSupportedLocale(Locale.FRANCE)).isEqualTo(Locale.FRANCE)
        assertThat(getSupportedLocale(Locale.FRENCH)).isEqualTo(Locale.FRANCE)
        assertThat(getSupportedLocale(Locale.CANADA_FRENCH)).isEqualTo(Locale.FRANCE)
    }

    @Test
    fun `getValidLanguages, unsupported languages`() {
        assertThat(getSupportedLocale(Locale.GERMAN)).isEqualTo(Locale.UK)
        assertThat(getSupportedLocale(Locale.ITALIAN)).isEqualTo(Locale.UK)
        assertThat(getSupportedLocale(Locale.CHINA)).isEqualTo(Locale.UK)
        assertThat(getSupportedLocale(Locale.JAPAN)).isEqualTo(Locale.UK)
    }
}