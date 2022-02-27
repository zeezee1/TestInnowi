package com.test.testinnowi.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.testinnowi.models.AlbumModel
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun stringToList(value: String): ArrayList<AlbumModel> {
        val listType: Type = object : TypeToken<ArrayList<AlbumModel>>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: ArrayList<AlbumModel>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}