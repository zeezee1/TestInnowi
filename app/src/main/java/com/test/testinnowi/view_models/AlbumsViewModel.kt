package com.test.testinnowi.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testinnowi.MyApplication
import com.test.testinnowi.database.DatabaseManager
import com.test.testinnowi.models.AlbumModel
import com.test.testinnowi.utils.Coroutines
import com.test.testinnowi.utils.Coroutines.run

class AlbumsViewModel(context: Context) : BaseViewModel(context) {

    private var mAlbums = MutableLiveData<ArrayList<AlbumModel>>()
    var albums: LiveData<ArrayList<AlbumModel>> = mAlbums

    fun getAlbums(showLoading: Boolean = true) {
        if (showLoading) loading(LoadingState.SHOW_PROGRESS)
        run {
            val response = restService.getAlbums()
            if (response.isSuccessful) {
                val albumsResponse: ArrayList<AlbumModel> = response.body()!!
                Coroutines.onIO {
                    saveToDb(albumsResponse)
                }
                Coroutines.onMain {
                    loading(LoadingState.HIDE_PROGRESS)
                    mAlbums.postValue(albumsResponse)
                }
            } else {
                handleError()
            }
        }
    }

    suspend fun saveToDb(albums: ArrayList<AlbumModel>) {
        DatabaseManager.getInstance().deleteAlbums()
        DatabaseManager.getInstance().saveAlbums(albums)
    }

    suspend fun getAlbumsFromDB(): ArrayList<AlbumModel> {
        return DatabaseManager.getInstance().getAlbums() as ArrayList<AlbumModel>
    }

    override fun handleError() {
        Coroutines.onMain {
            loading(LoadingState.HIDE_PROGRESS)
            mAlbums.postValue(getAlbumsFromDB())
        }
    }
}

class AlbumsViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumsViewModel(context) as T
        }
        throw IllegalStateException("Unknown ViewModel Class")
    }
}


val albumsViewModelFactory: AlbumsViewModelFactory
    get() = AlbumsViewModelFactory(MyApplication.getInstance().applicationContext)