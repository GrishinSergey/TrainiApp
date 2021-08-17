package com.sagrishin.uikit.utils

import android.content.Context
import com.sagrishin.common.utils.toDate
import java.text.DateFormat.MEDIUM
import java.text.DateFormat.SHORT
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import android.text.format.DateFormat as AndroidDateFormat
import java.text.DateFormat as JavaDateFormat

object DateTimeFormatUtils {

    val is24HourFormat: Boolean
        get() = AndroidDateFormat.is24HourFormat(context)
    private lateinit var context: Context

    fun init(context: Context) {
        if (!this::context.isInitialized) {
            DateTimeFormatUtils.context = context
        }
    }

}


fun getDateFormattedAsDayMonth(dateTime: LocalDate): String {
    return JavaDateFormat.getDateInstance(MEDIUM, Locale.getDefault()).format(dateTime.toDate())
}


/**
 *
 *
 */
fun getDateFormattedAsDevicesLocale(dateTime: LocalDateTime): String {
    return JavaDateFormat.getDateInstance(SHORT, Locale.getDefault()).format(dateTime.toDate())
}


/**
 * Returns [dateTime], formatted as time depends on device settings. This method checks, time format
 * settings (12/24) and formats dateTime according to them
 */
fun getTimeFormattedAsDevicesLocale(dateTime: LocalDateTime): String {
    return if (DateTimeFormatUtils.is24HourFormat) {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(dateTime.toLocalDate().toDate())
    } else {
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(dateTime.toLocalDate().toDate())
    }
}


fun getDateAndTimeFormattedAsDevicesLocale(dateTime: LocalDateTime): String {
    val formattedDate = getDateFormattedAsDevicesLocale(dateTime)
    val formattedTime = getTimeFormattedAsDevicesLocale(dateTime)
    return "$formattedDate $formattedTime"
}
