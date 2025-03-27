package com.martin.lucca.core.common.locale

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.FormatStyle
import java.util.Locale

class DateUtilKtTest {

    private val zoneId = ZoneId.of("Europe/Paris")

    @Test
    fun `displayDate, full`() {
        val date = LocalDate.of(2024, Month.JANUARY, 17)

        assertThat(
            date.displayDate(
                FormatStyle.FULL,
                Locale.FRANCE
            )
        ).isEqualTo("mercredi 17 janvier 2024")
        assertThat(
            date.displayDate(
                FormatStyle.FULL,
                Locale.UK
            )
        ).isEqualTo("Wednesday, 17 January 2024")
        assertThat(
            date.displayDate(
                FormatStyle.FULL,
                Locale.GERMANY
            )
        ).isEqualTo("Mittwoch, 17. Januar 2024")
    }

    @Test
    fun `displayDate, short`() {
        val date = LocalDate.of(2024, Month.JANUARY, 17)

        assertThat(date.displayDate(FormatStyle.SHORT, Locale.FRANCE)).isEqualTo("17/01/2024")
        assertThat(date.displayDate(FormatStyle.SHORT, Locale.UK)).isEqualTo("17/01/2024")
        assertThat(date.displayDate(FormatStyle.SHORT, Locale.GERMANY)).isEqualTo("17.01.24")
    }

    @Test
    fun `displayDate, default parameters`() {
        val date = LocalDate.of(2024, Month.JANUARY, 17)

        assertThat(date.displayDate(FormatStyle.FULL)).isEqualTo("Wednesday, 17 January 2024")
        assertThat(date.displayDate(locale = getSupportedLocale())).isEqualTo("Wednesday, 17 January 2024")
    }

    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `localdatetime, displayDate, full`() {
        val date = LocalDateTime.of(2024, Month.JANUARY, 17, 10, 30)

        assertThat(
            date.displayDate(
                FormatStyle.FULL,
                Locale.FRANCE
            )
        ).isEqualTo("mercredi 17 janvier 2024")
        assertThat(
            date.displayDate(
                FormatStyle.FULL,
                Locale.UK
            )
        ).isEqualTo("Wednesday, 17 January 2024")
        assertThat(
            date.displayDate(
                FormatStyle.FULL,
                Locale.GERMANY
            )
        ).isEqualTo("Mittwoch, 17. Januar 2024")
    }

    @Test
    fun `localdatetime, displayDate, short`() {
        val date = LocalDateTime.of(2024, Month.JANUARY, 17, 10, 30)

        assertThat(date.displayDate(FormatStyle.SHORT, Locale.FRANCE)).isEqualTo("17/01/2024")
        assertThat(date.displayDate(FormatStyle.SHORT, Locale.UK)).isEqualTo("17/01/2024")
        assertThat(date.displayDate(FormatStyle.SHORT, Locale.GERMANY)).isEqualTo("17.01.24")
    }

    @Test
    fun `localdatetime, displayDate, default parameters`() {
        val date = LocalDateTime.of(2024, Month.JANUARY, 17, 10, 30)

        assertThat(date.displayDate(FormatStyle.FULL)).isEqualTo("Wednesday, 17 January 2024")
        assertThat(date.displayDate(locale = getSupportedLocale())).isEqualTo("Wednesday, 17 January 2024")
    }

    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `displayHours, default parameters`() {

        assertThat(
            LocalDateTime.of(2024, Month.JANUARY, 17, 12, 35).displayHours()
        ).isEqualTo("12h35")
        assertThat(
            LocalDateTime.of(2024, Month.JANUARY, 17, 4, 0).displayHours()
        ).isEqualTo("04h00")
        assertThat(
            LocalDateTime.of(2024, Month.JANUARY, 17, 22, 1).displayHours()
        ).isEqualTo("22h01")
    }

    @Test
    fun `date time to epoch milli`() {

        assertThat(
            LocalDateTime.of(2024, Month.JANUARY, 17, 12, 35)
                .toEpochMilli(zoneId)
        ).isEqualTo(1705491300000)
        assertThat(
            LocalDateTime.of(2021, Month.NOVEMBER, 1, 4, 46)
                .toEpochMilli(zoneId)
        ).isEqualTo(1635738360000)
        assertThat(
            LocalDateTime.of(2000, Month.SEPTEMBER, 30, 1, 4)
                .toEpochMilli(zoneId)
        ).isEqualTo(970268640000)
    }

    @Test
    fun `date to epoch milli`() {

        assertThat(
            LocalDate.of(2024, Month.JANUARY, 1).toEpochMilli(zoneId)
        ).isEqualTo(1704063600000)
        assertThat(
            LocalDate.of(2021, Month.NOVEMBER, 1).toEpochMilli(zoneId)
        ).isEqualTo(1635721200000)
        assertThat(
            LocalDate.of(2000, Month.SEPTEMBER, 30).toEpochMilli(zoneId)
        ).isEqualTo(970264800000)
    }

    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun `date time to epoch seconds`() {

        assertThat(
            LocalDateTime.of(2024, Month.JANUARY, 17, 12, 35)
                .toEpochSeconds(zoneId)
        ).isEqualTo(1705491300)
        assertThat(
            LocalDateTime.of(2021, Month.NOVEMBER, 1, 4, 46)
                .toEpochSeconds(zoneId)
        ).isEqualTo(1635738360)
        assertThat(
            LocalDateTime.of(2000, Month.SEPTEMBER, 30, 1, 4)
                .toEpochSeconds(zoneId)
        ).isEqualTo(970268640)
    }

    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun toLocalDateTime() {
        assertThat(1719393955000L.toLocalDateTimeFromMilli(zoneId)).isEqualTo(
            LocalDateTime.of(2024, Month.JUNE, 26, 11, 25, 55)
        )
        assertThat("20240920_202020".toLocalDateTime(DATE_FORMAT_YMDHMS, Locale.FRANCE)).isEqualTo(
            LocalDateTime.of(2024, Month.SEPTEMBER, 20, 20, 20, 20)
        )
    }

    @Test
    fun toLocalDateTimeFromMilli() {
        assertThat(1719393955000L.toLocalDateTimeFromMilli(zoneId)).isEqualTo(
            LocalDateTime.of(2024, Month.JUNE, 26, 11, 25, 55)
        )
    }

    @Test
    fun toLocalDateTimeFromSecond() {
        assertThat(1719393950L.toLocalDateTimeFromSecond(zoneId)).isEqualTo(
            LocalDateTime.of(2024, Month.JUNE, 26, 11, 25, 50)
        )
    }
}