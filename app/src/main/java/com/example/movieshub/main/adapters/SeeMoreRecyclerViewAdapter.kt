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
import com.example.movieshub.main.interfaces.SeeMoreClickListener
import com.example.movieshub.main.models.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.widget_popular_movies_small.view.*

class SeeMoreRecyclerViewAdapter (private var dataList: ArrayList<Results>, private var isMovies: Boolean,private val context: Context,
                                  private val seeMoreClickListener: SeeMoreClickListener
) : RecyclerView.Adapter<ViewHolderSeeMore>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSeeMore {
        return ViewHolderSeeMore(LayoutInflater.from(context).inflate(R.layout.widget_popular_movies_small, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderSeeMore, position: Int) {

        if (isMovies){
            holder.title.text = dataList[position].title
        }
        else{
            holder.title.text = dataList[position].name
        }

        holder.desc.text = dataList[position].overview

        var iconName = dataList[position].poster_path
        val url = "https://image.tmdb.org/t/p/w500$iconName"
        Picasso.get().load(url).into(holder.icon)

        holder.rowFrame.setOnClickListener {
            seeMoreClickListener.onClickedFrame(dataList[position].id, isMovies)
        }
    }
}

class ViewHolderSeeMore (view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.movieTitle
    val desc: TextView = view.movieDesc
    val icon: ImageView = view.movieIcon
    val rowFrame: View = view

}