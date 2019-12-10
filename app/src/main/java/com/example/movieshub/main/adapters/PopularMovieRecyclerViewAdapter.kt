package com.example.movieshub.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieshub.R
import com.example.movieshub.main.interfaces.RecyclerViewItemClickListener
import com.example.movieshub.main.models.PopularMoviesModel
import com.example.movieshub.main.models.Results
import kotlinx.android.synthetic.main.widget_popular_movies.view.*

class PopularMovieRecyclerViewAdapter (private var dataList: ArrayList<Results>, private val context: Context,
                                       private val recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.widget_popular_movies, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataList[position].title
        holder.desc.text = dataList[position].overview
        holder.rowFrame.setOnClickListener {
            recyclerViewItemClickListener.onClicked(position)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.movieTitle
    val desc: TextView = view.movieDesc
    val rowFrame: View = view
}