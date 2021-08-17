package com.sagrishin.traini.domain.models

data class DomainMuscle(
    val id: Long,
    val group: Group
) {

    enum class Group {
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
