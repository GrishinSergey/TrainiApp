package com.sagrishin.traini.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sagrishin.traini.data.database.base.BaseLongIdEntity

@Entity
data class MuscleEntity constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long = 0,
    @ColumnInfo
    val muscleGroup: MuscleGroup
) : BaseLongIdEntity() {

    enum class MuscleGroup {
        NECK,
        TRAPEZE,
        SHOULDERS,
        CHEST,
        LATISSIMUS,
        BICEPS,
        TRICEPS,
        FOREARM,
        PRESS,
        BACK,
        QUADRICEPS,
        LEG_BICEPS,
        CALVES
    }

}
