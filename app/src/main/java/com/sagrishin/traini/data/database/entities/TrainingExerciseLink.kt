package com.sagrishin.traini.data.database.entities

import androidx.room.*
import com.sagrishin.traini.data.database.base.BaseLongIdEntity

@Entity(
    indices = [Index(value = ["trainingId", "exerciseId"], unique = true)]
)
class TrainingExerciseLink constructor(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    @ForeignKey(
        entity = TrainingEntity::class,
        parentColumns = ["id"],
        childColumns = ["trainingId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo
    val trainingId: Long,
    @ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
    @ColumnInfo
    val exerciseId: Long
) : BaseLongIdEntity()
