package com.sagrishin.traini.data.database.base

import androidx.room.Insert
import androidx.room.Transaction
import androidx.room.Update

abstract class BaseDao<T : BaseEntity> {

    @Insert
    abstract suspend fun create(entity: T): Long

    @Insert
    abstract suspend fun create(vararg entity: T)

    @Update
    abstract suspend fun update(entity: T): Int

    @Transaction
    open suspend fun save(entity: T) {
        val count = update(entity)
        if (count == 0) create(entity)
    }

}
