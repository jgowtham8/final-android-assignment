package com.example.movieshub.main.fragments

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.movieshub.R
import com.example.movieshub.main.adapters.PopularMovieRecyclerViewAdapter
import com.example.movieshub.main.interfaces.RecyclerViewItemClickListener
import com.example.movieshub.main.models.PopularMoviesModel
import com.example.movieshub.main.models.Results
import com.example.movieshub.main.services.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// : Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PopularFragment : Fragment() {

    //lateinit var loadingEffect: ProgressDialog
    var dataArr = ArrayList<PopularMoviesModel>()
    var responseResults = ArrayList<Results>()

    // : Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        loadingEffect = ProgressDialog(requireContext())
//        loadingEffect.setTitle("Loading..")
//        loadingEffect.setCancelable(false)
//        loadingEffect.show()

        loadPageContents()
    }

    private fun loadPageContents() {

        getData()
        popularRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        popularRecyclerView.adapter = PopularMovieRecyclerViewAdapter(responseResults,requireContext(), object : RecyclerViewItemClickListener {
            override fun onClicked(cityIndex: Int) {
            }
        })

    }

    companion object {
//        fun newInstance(param1: String, param2: String) =
//            PopularFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }

        fun newInstance(): PopularFragment =
            PopularFragment()
    }

    private fun getData() {
        val call: Call<PopularMoviesModel> = ApiClient.getClient.getInfo("c774f71cefc589b364526565067fbbc7")
        call.enqueue(object : Callback<PopularMoviesModel> {

            override fun onResponse(call: Call<PopularMoviesModel>?, response: Response<PopularMoviesModel>?) {
                //loadingEffect.dismiss()

                responseResults = response?.body()!!.results
                //popularRecyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PopularMoviesModel>?, t: Throwable?) {
                //loadingEffect.dismiss()
            }

        })
    }
}
