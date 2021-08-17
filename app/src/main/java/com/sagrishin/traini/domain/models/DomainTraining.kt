package com.sagrishin.traini.domain.models

import java.time.LocalDateTime

data class DomainTraining(
    val id: Long,
    val startDateTime: LocalDateTime
)
