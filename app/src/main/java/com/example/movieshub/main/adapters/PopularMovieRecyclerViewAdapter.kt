package com.example.movieshub.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshub.R
import com.example.movieshub.main.interfaces.RecyclerViewItemClickListener
import com.example.movieshub.main.models.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.*
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.categoryTitle
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.movieDesc
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.movieIcon
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.movieTitle

class PopularMovieRecyclerViewAdapter (private var dataList: ArrayList<Results>, private val context: Context,
                                       private val recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.widget_popular_movies_small, parent, false))
        if (viewType == 1){
            viewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.widget_popular_movies_big, parent, false))
        }

        return  viewHolder
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        var type = 0//normal
        if((position == 0) || (position == 6)){
            type = 1//big
        }
        return type
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < 6){
            holder.title.text = dataList[position].title
        }
        else{
            holder.title.text = dataList[position].name
        }

        holder.desc.text = dataList[position].overview

        var iconName = dataList[position].poster_path
        if ((position == 0) || (position == 6)){
            iconName = dataList[position].backdrop_path
        }
        val url = "https://image.tmdb.org/t/p/w500$iconName"

        if ((position == 0) || (position == 6)){
            Picasso.get().load(url).fit().centerCrop().into(holder.icon)
        }
        else{
            Picasso.get().load(url).into(holder.icon)
        }

        if (position == 0){
            holder.catHeading.text = "Movies"
        }
        if (position == 6){
            holder.catHeading.text = "TV Series"
        }

        var isMovie = true
        if (position > 5){
            isMovie = false
        }

        holder.rowFrame.setOnClickListener {
            recyclerViewItemClickListener.onClickedFrame(dataList[position].id, isMovie)
        }
        holder.seeMore.setOnClickListener {

            recyclerViewItemClickListener.onClickedSeeMore(dataList[position].id, isMovie)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.movieTitle
    val desc: TextView = view.movieDesc
    val catHeading:TextView = view.categoryTitle
    val icon: ImageView = view.movieIcon
    val seeMore:Button = view.btnSeeMore
    val rowFrame: View = view

}