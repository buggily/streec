package com.buggily.streec.data.streec

import androidx.paging.PagingData
import androidx.paging.map
import com.buggily.streec.core.data.GetInstant
import com.buggily.streec.local.streec.LocalStreec
import com.buggily.streec.local.streec.LocalStreecSourceable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
internal class StreecRepository(
    private val localStreecSource: LocalStreecSourceable,
    private val getInstant: GetInstant,
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
        val instant: Instant = getInstant()

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
        val instant: Instant = getInstant()

        streec.copy(
            name = name,
            modificationInstant = instant,
        ).let { localStreecSource.update(it) }
    }

    override suspend fun resetById(id: Long) {
        val streec: LocalStreec = localStreecSource.getById(id) ?: return
        val instant: Instant = getInstant()

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
