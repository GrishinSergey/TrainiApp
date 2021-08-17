package com.sagrishin.traini.domain.models

class DomainExerciseRepetition constructor(
    val id: Long,
    val trainingExerciseId: Long,
    val weight: Int,
    val repetitionsCount: Int
)
