package com.example.movieshub.main.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieshub.R
import com.example.movieshub.main.adapters.ActorsRvAdapter
import com.example.movieshub.main.helpers.Const
import com.example.movieshub.main.models.ActorsModel
import com.example.movieshub.main.models.Cast
import com.example.movieshub.main.models.MoviesAndTvModel
import com.example.movieshub.main.services.retrofit_client.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {


    val context:Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val isMovie = intent.extras!!.getBoolean("isMovie")
        val id = intent.extras!!.getInt("id")

        loadData(id, isMovie)
        loadActorData(id, isMovie)

        detailSwipeRefresh?.setOnRefreshListener {
            loadData(id, isMovie)
            loadActorData(id, isMovie)
            detailSwipeRefresh.isRefreshing = false
        }
    }

    private fun loadData(id:Int, isMovie:Boolean) {

        val call: Call<MoviesAndTvModel> = if (isMovie){
            ApiClientMovie.getClient.getInfo(id, Const.API_KEY)
        }
        else {
            ApiClientTV.getClient.getInfo(id, Const.API_KEY)
        }

        call.enqueue(object : Callback<MoviesAndTvModel> {

            override fun onResponse(call: Call<MoviesAndTvModel>?, response: Response<MoviesAndTvModel>?) {
                val x = response?.body()!!

                val iconImage = x.poster_path
                val bannerImage = x.backdrop_path
                val desc = x.overview
                val rating = x.vote_average.toString()
                var genreStr = ""

                var i = 0
                while (i < x.genres.size){
                    if (i > 2){
                        break
                    }
                    else if (i == 0){
                        genreStr += x.genres[i].name.toString()
                    }
                    else{
                        genreStr += "\n" + x.genres[i].name.toString()
                    }
                    i++
                }
                var nameOrTitle:String
                var year:String
                if (isMovie){
                    nameOrTitle = x.title.toString()
                    year = x.release_date.toString().take(4)
                }
                else{
                    nameOrTitle = x.name.toString()
                    year = x.last_air_date.toString().take(4)
                }

                //--------------------------------------------------------------------Binding

                tvTitleOrName.text = nameOrTitle
                tvDesc.text = desc
                tvYear.text = year
                tvRating.text = rating
                tvGenre.text = genreStr

                val urlIcon = "https://image.tmdb.org/t/p/w500$iconImage"
                val urlBanner = "https://image.tmdb.org/t/p/w500$bannerImage"
                Picasso.get().load(urlIcon).fit().centerCrop().into(ivIconImage)
                Picasso.get().load(urlBanner).fit().centerCrop().into(ivBannerImage)



            }

            override fun onFailure(call: Call<MoviesAndTvModel>?, t: Throwable?) {
                //loadingEffect.dismiss()
            }

        })
    }

//    private fun loadActorsRecyclerView(id:Int, isMovie:Boolean){
//
//        loadActorData(id, isMovie)
//
//    }

    private fun loadActorData(id:Int, isMovie:Boolean) {

        val call: Call<ActorsModel> = if (isMovie){
            ApiClientActorsMovie.getClient.getInfo(id, Const.API_KEY)
        }
        else {
            ApiClientActorsTV.getClient.getInfo(id, Const.API_KEY)
        }

        call.enqueue(object : Callback<ActorsModel> {

            override fun onResponse(call: Call<ActorsModel>?, response: Response<ActorsModel>?) {
                val actors = response?.body()!!
                val responseArr = ArrayList<Cast>()
                responseArr.clear()
                for (actor in  actors.cast){
                    responseArr.add(actor)
                }
                rvActors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                rvActors.adapter = ActorsRvAdapter(responseArr,context)
            }

            override fun onFailure(call: Call<ActorsModel>?, t: Throwable?) {
                //loadingEffect.dismiss()
            }

        })
    }
}
