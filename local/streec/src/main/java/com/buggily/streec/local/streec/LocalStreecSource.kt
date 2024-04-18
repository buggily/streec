package com.buggily.streec.local.streec

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

internal class LocalStreecSource(
    private val config: PagingConfig,
    private val localStreecDao: LocalStreecDao,
) : LocalStreecSourceable {

    override suspend fun getById(
        id: Long,
    ): LocalStreec? = localStreecDao.getById(
        id = id,
    )

    override fun getPaging(): Flow<PagingData<LocalStreec>> = Pager(
        config = config,
        pagingSourceFactory = { localStreecDao.getPaging() },
    ).flow

    override fun get(): Flow<List<LocalStreec>> = localStreecDao.get()

    override suspend fun insert(
        streec: LocalStreec,
    ) = localStreecDao.insert(
        streec = streec,
    )

    override suspend fun update(
        streec: LocalStreec,
    ) = localStreecDao.update(
        streec = streec,
    )

    override suspend fun delete(
        streec: LocalStreec,
    ) = localStreecDao.delete(
        streec = streec,
    )
}
