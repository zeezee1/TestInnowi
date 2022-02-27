package com.test.testinnowi.rest_api

import com.test.testinnowi.models.AlbumModel
import retrofit2.Response
import retrofit2.http.GET

interface RestService {

    @GET("photos")
    suspend fun getAlbums(): Response<ArrayList<AlbumModel>>
}