package com.sagrishin.traini.data.database.converters

import androidx.room.TypeConverter
import com.sagrishin.traini.data.database.entities.MuscleEntity

class MuscleGroupConverters {

    @TypeConverter
    fun toMuscleGroup(name: String): MuscleEntity.MuscleGroup {
        return MuscleEntity.MuscleGroup.valueOf(name)
    }

    @TypeConverter
    fun fromMuscleGroup(muscle: MuscleEntity.MuscleGroup): String {
        return muscle.name
    }

}
