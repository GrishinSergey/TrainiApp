package com.sagrishin.traini.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sagrishin.traini.data.database.base.BaseLongIdEntity

@Entity
class ExerciseEntity constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val description: String = "",
    @ForeignKey(
        entity = MuscleEntity::class,
        parentColumns = ["id"],
        childColumns = ["muscleId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo
    val muscleId: Long
) : BaseLongIdEntity()
