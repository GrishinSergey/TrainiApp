package com.sagrishin.traini.data.database.entities

import androidx.room.*
import com.sagrishin.traini.data.database.base.BaseLongIdEntity

@Entity(
    indices = [Index(value = ["trainingId", "exerciseId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = TrainingEntity::class,
            parentColumns = ["id"],
            childColumns = ["trainingId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
class TrainingExerciseLink constructor(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    @ColumnInfo
    val trainingId: Long,
    @ColumnInfo
    val exerciseId: Long
) : BaseLongIdEntity()
