package com.sagrishin.uikit.dialogs

import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateFormat
import java.time.LocalTime

@FunctionalInterface
fun interface OnTimeSelectListener {
    fun onSelected(localTime: LocalTime)
}


fun Context.showTimePicker(calendar: LocalTime, listener: OnTimeSelectListener) {
    val dialog = TimePickerDialog(
        this,
        { _, hourOfDay, minute -> listener.onSelected(LocalTime.of(hourOfDay, minute)) },
        calendar.hour,
        calendar.minute,
        DateFormat.is24HourFormat(this)
    )
    dialog.show()
}
