package com.sagrishin.traini.domain.converters

import com.sagrishin.traini.data.database.entities.MuscleEntity
import com.sagrishin.traini.domain.models.DomainMuscle
import com.sagrishin.traini.presentation.uimodels.UiMuscleGroup

fun DomainMuscle.toUiModel(): UiMuscleGroup {
    return when (group) {
        DomainMuscle.Group.NECK -> UiMuscleGroup.NECK
        DomainMuscle.Group.TRAPEZE -> UiMuscleGroup.TRAPEZE
        DomainMuscle.Group.SHOULDERS -> UiMuscleGroup.SHOULDERS
        DomainMuscle.Group.CHEST -> UiMuscleGroup.CHEST
        DomainMuscle.Group.LATISSIMUS -> UiMuscleGroup.LATISSIMUS
        DomainMuscle.Group.BICEPS -> UiMuscleGroup.BICEPS
        DomainMuscle.Group.TRICEPS -> UiMuscleGroup.TRICEPS
        DomainMuscle.Group.FOREARM -> UiMuscleGroup.FOREARM
        DomainMuscle.Group.PRESS -> UiMuscleGroup.PRESS
        DomainMuscle.Group.BACK -> UiMuscleGroup.BACK
        DomainMuscle.Group.QUADRICEPS -> UiMuscleGroup.QUADRICEPS
        DomainMuscle.Group.LEG_BICEPS -> UiMuscleGroup.LEG_BICEPS
        DomainMuscle.Group.CALVES -> UiMuscleGroup.CALVES
    }
}


fun DomainMuscle.Group.toEntity(): MuscleEntity.MuscleGroup {
    return when (this) {
        DomainMuscle.Group.NECK -> MuscleEntity.MuscleGroup.NECK
        DomainMuscle.Group.TRAPEZE -> MuscleEntity.MuscleGroup.TRAPEZE
        DomainMuscle.Group.SHOULDERS -> MuscleEntity.MuscleGroup.SHOULDERS
        DomainMuscle.Group.CHEST -> MuscleEntity.MuscleGroup.CHEST
        DomainMuscle.Group.LATISSIMUS -> MuscleEntity.MuscleGroup.LATISSIMUS
        DomainMuscle.Group.BICEPS -> MuscleEntity.MuscleGroup.BICEPS
        DomainMuscle.Group.TRICEPS -> MuscleEntity.MuscleGroup.TRICEPS
        DomainMuscle.Group.FOREARM -> MuscleEntity.MuscleGroup.FOREARM
        DomainMuscle.Group.PRESS -> MuscleEntity.MuscleGroup.PRESS
        DomainMuscle.Group.BACK -> MuscleEntity.MuscleGroup.BACK
        DomainMuscle.Group.QUADRICEPS -> MuscleEntity.MuscleGroup.QUADRICEPS
        DomainMuscle.Group.LEG_BICEPS -> MuscleEntity.MuscleGroup.LEG_BICEPS
        DomainMuscle.Group.CALVES -> MuscleEntity.MuscleGroup.CALVES
    }
}
