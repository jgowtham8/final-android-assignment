package com.example.movieshub.main.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieshub.R
import com.example.movieshub.main.adapters.SeeMoreRecyclerViewAdapter
import com.example.movieshub.main.helpers.Const
import com.example.movieshub.main.interfaces.SeeMoreClickListener
import com.example.movieshub.main.models.PopularMoviesTvModel
import com.example.movieshub.main.models.Results
import com.example.movieshub.main.services.retrofit_client.ApiClientPopMovie
import com.example.movieshub.main.services.retrofit_client.ApiClientPopTV
import kotlinx.android.synthetic.main.activity_see_more.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeeMoreActivity : AppCompatActivity() {

    var responseArr = ArrayList<Results>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_more)
        seeMoreSwipeRefresh.isRefreshing = true
        loadPageContents()
        seeMoreSwipeRefresh.isRefreshing = false

        seeMoreSwipeRefresh?.setOnRefreshListener {
            responseArr.clear()
            loadPageContents()
            seeMoreSwipeRefresh.isRefreshing = false
        }
    }

    private fun loadPageContents() {
        val isMovie = intent.extras!!.getBoolean("isMovie")
        if (isMovie){
            getDataOfPopMovie(1)
//            getDataOfPopMovie(2)
//            getDataOfPopMovie(3)
        }
        else{
            getDataOfPopTV(1)
//            getDataOfPopTV(2)
//            getDataOfPopTV(3)
        }

        seeMoreRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        seeMoreRecyclerView.adapter = SeeMoreRecyclerViewAdapter(responseArr, isMovie,this, object : SeeMoreClickListener {
            override fun onClickedFrame(id: Int, isMovie: Boolean) {
                //Toast.makeText(this@SeeMoreActivity,id.toString()+"\n"+isMovie.toString(), Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SeeMoreActivity, DetailActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("isMovie",isMovie)
                startActivity(intent)
            }
        })

    }

    private fun getDataOfPopMovie(pageNo:Int) {
        val call: Call<PopularMoviesTvModel> = ApiClientPopMovie.getClient.getInfo(Const.API_KEY,pageNo)
        call.enqueue(object : Callback<PopularMoviesTvModel> {

            override fun onResponse(call: Call<PopularMoviesTvModel>?, response: Response<PopularMoviesTvModel>?) {

                var i = 0
                while (i < response?.body()!!.results.size){
                    responseArr.add(response.body()!!.results[i])
                    i++
                }


                seeMoreRecyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PopularMoviesTvModel>?, t: Throwable?) {
                //loadingEffect.dismiss()
            }

        })
    }

    private fun getDataOfPopTV(pageNo:Int) {
        val call: Call<PopularMoviesTvModel> = ApiClientPopTV.getClient.getInfo(Const.API_KEY,pageNo)
        call.enqueue(object : Callback<PopularMoviesTvModel> {

            override fun onResponse(call: Call<PopularMoviesTvModel>?, response: Response<PopularMoviesTvModel>?) {
                var i = 0
                while (i < response?.body()!!.results.size){
                    responseArr.add(response.body()!!.results[i])
                    i++
                }


                seeMoreRecyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PopularMoviesTvModel>?, t: Throwable?) {
                //loadingEffect.dismiss()
            }

        })
    }
}
