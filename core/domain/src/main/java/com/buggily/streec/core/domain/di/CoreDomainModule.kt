package com.buggily.streec.core.domain.di

import com.buggily.streec.core.domain.GetInstant
import com.buggily.streec.core.domain.GetStreecText
import com.buggily.streec.core.domain.GetTimeZone
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object CoreDomainModule {

    @Provides
    fun providesGetInstant(): GetInstant = GetInstant()

    @Provides
    fun providesGetTimeZone(): GetTimeZone = GetTimeZone()

    @Provides
    fun providesGetStreecText(
        getInstant: GetInstant,
        getTimeZone: GetTimeZone,
    ): GetStreecText = GetStreecText(
        getInstant = getInstant,
        getTimeZone = getTimeZone,
    )
}
