package com.buggily.streec.data.streec

import androidx.paging.PagingData
import androidx.paging.map
import com.buggily.streec.local.streec.LocalStreec
import com.buggily.streec.local.streec.LocalStreecSourceable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal class StreecRepository(
    private val localStreecSource: LocalStreecSourceable,
) : StreecRepositable {

    override suspend fun getById(
        id: Long,
    ): Streec? = localStreecSource.getById(
        id = id,
    )?.to()

    override fun getPaging(): Flow<PagingData<Streec>> = localStreecSource.getPaging().map {
        it.map { streec: LocalStreec -> streec.to() }
    }

    override fun get(): Flow<List<Streec>> = localStreecSource.get().map {
        it.map { streec: LocalStreec -> streec.to() }
    }

    override suspend fun create(name: String) {
        val instant: Instant = Clock.System.now()

        Streec(
            id = 0,
            name = name,
            resetInstant = instant,
        ).toLocal(
            creationInstant = instant,
            modificationInstant = instant,
        ).let { localStreecSource.insert(it) }
    }

    override suspend fun updateById(
        id: Long,
        name: String,
    ) {
        val streec: LocalStreec = localStreecSource.getById(id) ?: return
        val instant: Instant = Clock.System.now()

        streec.copy(
            name = name,
            modificationInstant = instant,
        ).let { localStreecSource.update(it) }
    }

    override suspend fun resetById(id: Long) {
        val streec: LocalStreec = localStreecSource.getById(id) ?: return
        val instant: Instant = Clock.System.now()

        streec.copy(
            resetInstant = instant,
            modificationInstant = instant,
        ).let { localStreecSource.update(it) }
    }

    override suspend fun deleteById(id: Long) {
        val streec: LocalStreec = localStreecSource.getById(id) ?: return
        localStreecSource.delete(streec)
    }
}
