package com.buggily.streec.local.core

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

internal class InstantTypeConverter {

    @TypeConverter
    fun to(epochMilliseconds: Long): Instant = Instant.fromEpochMilliseconds(epochMilliseconds)

    @TypeConverter
    fun from(instant: Instant): Long = instant.toEpochMilliseconds()
}
