package com.buggily.streec.local.core.di

import android.content.Context
import androidx.room.Room
import com.buggily.streec.local.core.StreecDatabase
import com.buggily.streec.local.core.StreecDatabaseable
import com.buggily.streec.local.streec.LocalStreecDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreLocalModule {

    @Provides
    fun providesLocalStreecDao(
        streecDatabase: StreecDatabaseable,
    ): LocalStreecDao = streecDatabase.getLocalStreecDao()

    @Provides
    @Singleton
    fun providesStreecDatabaseable(
        @ApplicationContext context: Context,
    ): StreecDatabaseable = Room.databaseBuilder(
        context = context,
        klass = StreecDatabase::class.java,
        name = NAME,
    ).build()

    private const val NAME = "streec.db"
}
