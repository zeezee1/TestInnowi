package com.test.testinnowi.database

import androidx.room.Room
import com.test.testinnowi.MyApplication
import com.test.testinnowi.models.AlbumModel

class DatabaseManager {

    private var database: MyDatabase = Room.databaseBuilder(MyApplication.getInstance().applicationContext, MyDatabase::class.java, "RoomDatabase").allowMainThreadQueries().build()

    companion object {
        private var instant: DatabaseManager? = null

        public fun getInstance(): DatabaseManager {
            if (instant == null) {
                instant = DatabaseManager()
            }
            return instant!!
        }
    }

    suspend fun getAlbums(): List<AlbumModel> {
        return database.getAlbumDao().getAllAlbums()
    }

    suspend fun saveAlbums(albums: ArrayList<AlbumModel>) {
        return database.getAlbumDao().saveAlbums(*(albums.toTypedArray()))
    }

    suspend fun deleteAlbums() {
        database.getAlbumDao().deleteAll()
    }

}