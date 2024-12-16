package com.buggily.streec.data.streec.di

import com.buggily.streec.data.streec.StreecRepositable
import com.buggily.streec.data.streec.StreecRepository
import com.buggily.streec.local.streec.LocalStreecSourceable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataStreecModule {

    @Provides
    fun provides(
        localStreecSource: LocalStreecSourceable,
    ): StreecRepositable = StreecRepository(
        localStreecSource = localStreecSource,
    )
}
