package com.buggily.streec.data.streec

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface StreecRepositable {

    suspend fun getById(id: Long): Streec?
    fun getPaging(): Flow<PagingData<Streec>>
    fun get(): Flow<List<Streec>>

    suspend fun create(name: String)
    suspend fun resetById(id: Long)
    suspend fun deleteById(id: Long)

    suspend fun updateById(
        id: Long,
        name: String,
    )
}
