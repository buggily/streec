package com.buggily.streec.local.streec

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalStreecDao {

    @Query(
        """
            SELECT * FROM $TABLE_NAME
            WHERE ${LocalStreec.ID} = :id
        """
    )

    suspend fun getById(id: Long): LocalStreec?

    @Query(
        """
            SELECT * FROM $TABLE_NAME
            ORDER BY ${LocalStreec.MODIFICATION_INSTANT} ASC
        """
    )

    fun getPaging(): PagingSource<Int, LocalStreec>

    @Query(
        """
            SELECT * FROM $TABLE_NAME
            ORDER BY ${LocalStreec.MODIFICATION_INSTANT} ASC
        """
    )

    fun get(): Flow<List<LocalStreec>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(streec: LocalStreec)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(streec: LocalStreec)

    @Delete
    suspend fun delete(streec: LocalStreec)

    companion object {
        const val TABLE_NAME = "streec"
    }
}
