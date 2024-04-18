package com.buggily.streec.local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.buggily.streec.local.streec.LocalStreec
import com.buggily.streec.local.streec.LocalStreecDao

@Database(

    entities = [
        LocalStreec::class,
    ],

    exportSchema = false,
    version = 1,
)

@TypeConverters(InstantTypeConverter::class)
abstract class StreecDatabase : RoomDatabase(), StreecDatabaseable {
    abstract override fun getLocalStreecDao(): LocalStreecDao
}
