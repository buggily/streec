package com.buggily.streec.local.streec.di

import androidx.paging.PagingConfig
import com.buggily.streec.local.streec.LocalStreecDao
import com.buggily.streec.local.streec.LocalStreecSource
import com.buggily.streec.local.streec.LocalStreecSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalStreecModule {

    @Provides
    fun providesLocalStreecSourceable(
        localStreecDao: LocalStreecDao,
    ): LocalStreecSourceable = LocalStreecSource(
        config = PagingConfig(pageSize = 10),
        localStreecDao = localStreecDao,
    )
}
