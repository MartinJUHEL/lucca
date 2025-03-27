package com.martin.lucca.core.common.locale

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

const val DATE_FORMAT_YMD = "yyyy-MM-dd"
const val DATE_FORMAT_DMY = "dd/MM/yyyy"
const val DATE_FORMAT_MMYY = "MM/yy"
const val DATE_FORMAT_DD = "dd"
const val DATE_FORMAT_MM = "MM"
const val DATE_FORMAT_DMMM = "d MMMM"
const val DATE_FORMAT_HH_MM = "HH'h'mm"
const val DATE_FORMAT_HH_COLON_MM = "HH':'mm"
const val DATE_FORMAT_YMDHMS = "yyyyMMdd_HHmmss"
const val DATE_FORMAT_YMDTMSZ = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_FORMAT_YMDTMS = "yyyy-MM-dd'T'HH:mm:ss"
const val DATE_FORMAT_DD_MMMM_YYYY = "dd MMMM yyyy"

private const val MILLIS = 1000L

/**
 * @return a displayed date, formatted according to the current locale, restricted to the locales the app supports.
 * @param formatStyle the format. For example, in "full":
 * FR: "mercredi 17 janvier 2024".
 * EN: "Wednesday, 17 January 2024".
 * DE: "Mittwoch, 17. Januar 2024".
 * @param locale the possible locale override. Shouldn't be used, especially used only for testing.
 * @return the displayable date.
 */
fun LocalDate.displayDate(
    formatStyle: FormatStyle = FormatStyle.FULL,
    locale: Locale = getSupportedLocale()
): String {
    return format(DateTimeFormatter.ofLocalizedDate(formatStyle).withLocale(locale))
}

/**
 * @return a displayed date, formatted according to the current locale, restricted to the locales the app supports.
 * @param formatStyle the format. For example, in "full":
 * FR: "mercredi 17 janvier 2024".
 * EN: "Wednesday, 17 January 2024".
 * DE: "Mittwoch, 17. Januar 2024".
 * @param locale the possible locale override. Shouldn't be used, especially used only for testing.
 * @return the displayable date.
 */
fun LocalDateTime.displayDate(
    formatStyle: FormatStyle = FormatStyle.FULL,
    locale: Locale = getSupportedLocale()
): String {
    return format(DateTimeFormatter.ofLocalizedDate(formatStyle).withLocale(locale))
}

/**
 * @return a displayed date, according to the given format.
 * @param inputFormat the format. For example, "dd/MM/yyyy" (see [DATE_FORMAT_DMY]).
 */
fun LocalDate.displayDate(inputFormat: String): String {
    return format(DateTimeFormatter.ofPattern(inputFormat))
}

/** @return day of month with two digits */
fun LocalDate.getTwoDigitDayOfMonth(locale: Locale = getSupportedLocale()): String =
    format(DateTimeFormatter.ofPattern(DATE_FORMAT_DD, locale))

/** @return month with two digits */
fun LocalDate.getTwoDigitMonth(locale: Locale = getSupportedLocale()): String =
    format(DateTimeFormatter.ofPattern(DATE_FORMAT_MM, locale))

/**
 * Converts a String to a LocalDate.
 * @param inputFormat the input pattern, such as "yyy-MM-dd" for example ([DATE_FORMAT_YMD]).
 * @param locale the possible locale override. Shouldn't be used, especially used only for testing.
 */
fun String.toLocalDate(inputFormat: String, locale: Locale = getSupportedLocale()): LocalDate {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(inputFormat, locale)
    return LocalDate.parse(this, dateTimeFormatter)
}

/**
 * Converts a String to a LocalDateTime.
 * @param inputFormat the input pattern, such as "yyy-MM-dd" for example ([DATE_FORMAT_YMD]).
 * @param locale the possible locale override. Shouldn't be used, especially used only for testing.
 */
fun String.toLocalDateTime(
    inputFormat: String,
    locale: Locale = getSupportedLocale()
): LocalDateTime {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(inputFormat, locale)
    return LocalDateTime.parse(this, dateTimeFormatter)
}

/** @return a Date (12h00) from the given [LocalDateTime]. */
fun LocalDateTime.displayHours(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_HH_MM)
    return format(dateTimeFormatter)
}

/**
 * @return a [LocalDate] from an Epoch millis number.
 * From https://stackoverflow.com/a/35186993
 */
fun Long.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalDate()
}

/**
 * @return a [LocalDateTime] from an Epoch millis number.
 * From https://stackoverflow.com/a/35186993
 */
fun Long.toLocalDateTimeFromMilli(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return Instant.ofEpochMilli(this)
        .atZone(zoneId)
        .toLocalDateTime()
}

/**
 * @return a [LocalDateTime] from an Epoch second number.
 * From https://stackoverflow.com/a/35186993
 */
fun Long.toLocalDateTimeFromSecond(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return Instant.ofEpochSecond(this)
        .atZone(zoneId)
        .toLocalDateTime()
}

/** @return a [Long] from an [LocalDateTime]. */
fun LocalDateTime.toEpochMilli(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return ZonedDateTime.of(this, zoneId).toInstant().toEpochMilli()
}

/** @return a [Long] from an [LocalDate]. */
fun LocalDate.toEpochMilli(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return atStartOfDay().toEpochMilli(zoneId)
}

/** @return a [Long] from an [LocalDateTime]. */
fun LocalDateTime.toEpochSeconds(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return ZonedDateTime.of(this, zoneId).toInstant().toEpochMilli() / MILLIS
}

/**
 * @return a displayed date, according to the given format.
 * @param inputFormat the format. For example, "dd/MM/yyyy" (see [DATE_FORMAT_DMY]).
 */
fun LocalDateTime.displayDate(inputFormat: String): String {
    return format(DateTimeFormatter.ofPattern(inputFormat))
}