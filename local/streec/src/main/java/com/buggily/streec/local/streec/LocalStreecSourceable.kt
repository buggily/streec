package com.buggily.streec.local.streec

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface LocalStreecSourceable {

    suspend fun getById(id: Long): LocalStreec?
    fun getPaging(): Flow<PagingData<LocalStreec>>
    fun get(): Flow<List<LocalStreec>>

    suspend fun insert(streec: LocalStreec)
    suspend fun update(streec: LocalStreec)
    suspend fun delete(streec: LocalStreec)
}
