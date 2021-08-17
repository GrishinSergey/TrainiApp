package com.sagrishin.traini.data.database.base

abstract class BaseIdEntity<T> : BaseEntity() {

    abstract val id: T

}