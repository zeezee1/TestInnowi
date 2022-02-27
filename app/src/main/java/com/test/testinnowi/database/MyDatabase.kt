package com.test.testinnowi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.testinnowi.database.AlbumDao
import com.test.testinnowi.database.Converters
import com.test.testinnowi.models.AlbumModel

@Database(entities = [AlbumModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getAlbumDao(): AlbumDao
}