package com.example.movieshub.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshub.R
import com.example.movieshub.main.interfaces.RecyclerViewItemClickListener
import com.example.movieshub.main.models.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.*

class PopularMovieRecyclerViewAdapter (private var dataList: List<Results>, private val context: Context,
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

    override fun getItemViewType(position: Int): Int {
        var type = 0//normal
        if(position == 0){
            type = 1//big
        }
        return type
        //return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.title.text = dataList[position].name
        holder.desc.text = dataList[position].overview

        var iconName = dataList[position].poster_path
        if (position == 0){
            iconName = dataList[position].backdrop_path
        }
        val url = "https://image.tmdb.org/t/p/w500$iconName"
        Picasso.get().load(url).into(holder.icon)
        holder.rowFrame.setOnClickListener {
            recyclerViewItemClickListener.onClicked(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.movieTitle
    val desc: TextView = view.movieDesc
    val icon: ImageView = view.movieIcon
    val rowFrame: View = view
}