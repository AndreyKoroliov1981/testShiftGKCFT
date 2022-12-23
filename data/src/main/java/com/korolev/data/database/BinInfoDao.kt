package com.korolev.data.database

import androidx.room.*
import com.korolev.data.database.model.BinInfoDB

@Dao
interface BinInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(binInfoDB: BinInfoDB): Long

    @Delete
    suspend fun delete(binInfoDB: BinInfoDB)

    @Query("DELETE FROM saved_request")
    suspend fun deleteAllHistory()


    @Query("SELECT * FROM saved_request")
    suspend fun getAllHistory(): List<BinInfoDB>

    @Query("SELECT * FROM saved_request WHERE id = :recordId")
    suspend fun getById(recordId: Long): BinInfoDB
}