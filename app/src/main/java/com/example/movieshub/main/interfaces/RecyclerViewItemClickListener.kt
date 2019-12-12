package com.example.movieshub.main.interfaces

interface RecyclerViewItemClickListener {
    fun onClickedFrame(id: Int,isMovie:Boolean)
    fun onClickedSeeMore(id: Int, isMovie:Boolean)

}