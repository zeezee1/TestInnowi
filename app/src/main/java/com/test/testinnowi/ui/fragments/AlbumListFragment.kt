package com.test.testinnowi.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.testinnowi.R
import com.test.testinnowi.databinding.FragmentAlbumListBinding
import com.test.testinnowi.models.AlbumModel
import com.test.testinnowi.ui.adapters.AlbumsAdapter
import com.test.testinnowi.view_models.BaseViewModel
import com.test.testinnowi.view_models.AlbumsViewModel
import com.test.testinnowi.view_models.albumsViewModelFactory

class AlbumListFragment : BaseFragment<FragmentAlbumListBinding>() {

    private val albumsViewModel: AlbumsViewModel by viewModels {
        albumsViewModelFactory
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_album_list
    }

    override fun initViews() {
        registerObservers()
        binding.swipeLayout.setOnRefreshListener {
            albumsViewModel.getAlbums(false)
        }
        albumsViewModel.getAlbums()
    }

    private fun registerObservers() {
        albumsViewModel.albums.observe(viewLifecycleOwner) {
            binding.swipeLayout.isRefreshing = false
            if (it.isEmpty()) {
                binding.tvNoResult.visibility = View.VISIBLE
            } else {
                binding.adapter = AlbumsAdapter(it)
                binding.tvNoResult.visibility = View.GONE
            }
        }

        albumsViewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                BaseViewModel.LoadingState.SHOW_PROGRESS -> showProgressDialog()
                BaseViewModel.LoadingState.HIDE_PROGRESS -> hideProgressDialog()
            }
        }

        albumsViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }
    }
}