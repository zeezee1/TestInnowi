package com.test.testinnowi.utils

import com.test.testinnowi.models.AlbumModel

class AlbumsComparator: Comparator<AlbumModel> {
    override fun compare(o1: AlbumModel?, o2: AlbumModel?): Int {
        return Integer.valueOf(o1!!.albumId).compareTo(o2!!.albumId)
    }

}