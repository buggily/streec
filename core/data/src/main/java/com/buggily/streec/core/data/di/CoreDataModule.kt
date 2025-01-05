package com.buggily.streec.core.data.di

import com.buggily.streec.core.data.GetInstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object CoreDataModule {

    @Provides
    fun provides(): GetInstant = GetInstant()
}
