package com.test.testinnowi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.testinnowi.databinding.RowAlbumBinding
import com.test.testinnowi.models.AlbumModel
import com.test.testinnowi.utils.AlbumsComparator
import java.util.*
import kotlin.collections.ArrayList

class AlbumsAdapter(var albums: ArrayList<AlbumModel>) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    init {
        Collections.sort(albums, AlbumsComparator())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: RowAlbumBinding = RowAlbumBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindView(albums[position])
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumViewHolder(var layout: RowAlbumBinding) : RecyclerView.ViewHolder(layout.root) {
        fun bindView(album: AlbumModel) {
            layout.album = album
            Picasso.get().load(album.thumbnailUrl).into(layout.ivThumbnail);
        }
    }
}