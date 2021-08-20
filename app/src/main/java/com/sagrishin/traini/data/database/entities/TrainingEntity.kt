package com.sagrishin.traini.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sagrishin.traini.data.database.base.BaseEntity
import java.time.LocalDateTime

@Entity
class TrainingEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val startDate: LocalDateTime
): BaseEntity()
