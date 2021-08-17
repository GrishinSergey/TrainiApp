package com.sagrishin.traini.presentation.uimodels

import java.time.LocalDateTime

data class UiTrainingData constructor(
    val id: Long,
    val scheduledDateTime: LocalDateTime,
)
