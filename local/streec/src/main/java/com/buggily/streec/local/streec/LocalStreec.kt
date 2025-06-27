package com.buggily.streec.local.streec

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Entity(tableName = LocalStreecDao.TABLE_NAME)
data class LocalStreec(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = RESET_INSTANT)
    val resetInstant: Instant,

    @ColumnInfo(name = CREATION_INSTANT)
    val creationInstant: Instant,

    @ColumnInfo(name = MODIFICATION_INSTANT)
    val modificationInstant: Instant,
) {

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val RESET_INSTANT = "reset_instant"
        const val CREATION_INSTANT = "creation_instant"
        const val MODIFICATION_INSTANT = "modification_instant"
    }
}
