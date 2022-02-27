package com.test.testinnowi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.test.testinnowi.models.AlbumModel

@Dao
interface AlbumDao {
    @Query("DELETE FROM Albums")
    suspend fun deleteAll()

    @Query("SELECT * FROM Albums")
    suspend fun getAllAlbums(): List<AlbumModel>

    @Insert(onConflict = IGNORE)
    suspend fun saveAlbums(vararg album: AlbumModel)
}