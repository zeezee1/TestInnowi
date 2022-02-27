package com.test.testinnowi.view_models

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.testinnowi.models.AlbumModel
import com.test.testinnowi.utils.Coroutines
import junit.framework.TestCase
import kotlinx.coroutines.test.withTestContext
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsViewModelTest : TestCase() {
    lateinit var viewModel: AlbumsViewModel

    override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = AlbumsViewModel((context))
    }

    @Before
    @Test
    fun viewModelSavesData() {
        val testAlbums = ArrayList<AlbumModel>()
        testAlbums.add(AlbumModel(1, 1, "Spiderman 1", "", "https://image.tmdb.org/t/p/w500/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        testAlbums.add(AlbumModel(1, 2, "Spiderman 2", "", "https://image.tmdb.org/t/p/w500/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        testAlbums.add(AlbumModel(2, 3, "Spiderman 3", "", "https://image.tmdb.org/t/p/w500/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        testAlbums.add(AlbumModel(2, 4, "Spiderman 4", "", "https://image.tmdb.org/t/p/w500/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        testAlbums.add(AlbumModel(3, 5, "Spiderman 5", "", "https://image.tmdb.org/t/p/w500/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        testAlbums.add(AlbumModel(1, 1, "Spiderman 6", "", "https://image.tmdb.org/t/p/w500/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        Coroutines.onIO {
            viewModel.saveToDb(testAlbums)
        }
    }

    @After
    @Test
    fun viewModelGetsData() {
        Coroutines.onIO {
            val albums = viewModel.getAlbumsFromDB()
            withTestContext {
                albums.forEach {
                    Log.d("Album title", it.title)
                }
            }
        }
    }
}