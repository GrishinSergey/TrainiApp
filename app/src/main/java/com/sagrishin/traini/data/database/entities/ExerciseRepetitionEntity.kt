package com.sagrishin.traini.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sagrishin.traini.data.database.base.BaseEntity

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = TrainingExerciseLink::class,
            parentColumns = ["id"],
            childColumns = ["trainingExerciseId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
class ExerciseRepetitionEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val trainingExerciseId: Long,
    @ColumnInfo
    val weight: Int,
    @ColumnInfo
    val repetitionsCount: Int
) : BaseEntity()
