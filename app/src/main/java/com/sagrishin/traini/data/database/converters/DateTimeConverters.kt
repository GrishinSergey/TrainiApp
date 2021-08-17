package com.sagrishin.traini.data.database.converters

import androidx.room.TypeConverter
import java.time.*

class DateTimeConverters {

    @TypeConverter
    fun getLocalDateTimeFromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDateTime() }
    }

    @TypeConverter
    fun toTimestamp(value: LocalDateTime?): Long? {
        return value?.let { value.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli() }
    }

    @TypeConverter
    fun getLocalDateFromTimestamp(value: Long?): LocalDate? {
        return value?.let { Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate() }
    }

    @TypeConverter
    fun toTimestamp(value: LocalDate?): Long? {
        return value?.let { value.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli() }
    }

}
