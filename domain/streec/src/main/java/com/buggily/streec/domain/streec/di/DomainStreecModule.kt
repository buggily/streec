package com.buggily.streec.domain.streec.di

import com.buggily.streec.core.domain.GetStreecText
import com.buggily.streec.data.streec.StreecRepositable
import com.buggily.streec.domain.streec.CreateStreec
import com.buggily.streec.domain.streec.DeleteStreecById
import com.buggily.streec.domain.streec.GetStreecById
import com.buggily.streec.domain.streec.GetStreecPaging
import com.buggily.streec.domain.streec.GetStreecs
import com.buggily.streec.domain.streec.ResetStreecById
import com.buggily.streec.domain.streec.UpdateStreecById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DomainStreecModule {

    @Provides
    fun providesCreateStreec(
        streecRepository: StreecRepositable,
    ): CreateStreec = CreateStreec(
        streecRepository = streecRepository,
    )

    @Provides
    fun providesDeleteStreecById(
        streecRepository: StreecRepositable,
    ): DeleteStreecById = DeleteStreecById(
        streecRepository = streecRepository,
    )

    @Provides
    fun providesGetStreecById(
        streecRepository: StreecRepositable,
        getStreecText: GetStreecText,
    ): GetStreecById = GetStreecById(
        streecRepository = streecRepository,
        getStreecText = getStreecText,
    )

    @Provides
    fun providesGetStreecPaging(
        streecRepository: StreecRepositable,
        getStreecText: GetStreecText,
    ): GetStreecPaging = GetStreecPaging(
        streecRepository = streecRepository,
        getStreecText = getStreecText,
    )

    @Provides
    fun providesGetStreecs(
        streecRepository: StreecRepositable,
        getStreecText: GetStreecText,
    ): GetStreecs = GetStreecs(
        streecRepository = streecRepository,
        getStreecText = getStreecText,
    )

    @Provides
    fun providesResetStreecById(
        streecRepository: StreecRepositable,
    ): ResetStreecById = ResetStreecById(
        streecRepository = streecRepository,
    )

    @Provides
    fun providesUpdateStreecById(
        streecRepository: StreecRepositable,
    ): UpdateStreecById = UpdateStreecById(
        streecRepository = streecRepository,
    )
}
