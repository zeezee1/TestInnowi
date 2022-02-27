package com.test.testinnowi.models

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "Albums")
class AlbumModel(var albumId: Int, @PrimaryKey var id: Int, var title: String, var url: String, var thumbnailUrl: String): Serializable