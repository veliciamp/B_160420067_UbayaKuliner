package com.ubaya.ubayakuliner160420067.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakuliner160420067.model.Kuliner
import com.ubaya.ubayakuliner160420067.model.Review

class ListReviewViewModel(application: Application): AndroidViewModel(application) {
    val reviewLD = MutableLiveData<ArrayList<Review>>()
    val reviewLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(idDetailReview:String){
//        val review1 = Review("1","1","VMP","gaenak")
//        val review2 = Review("1","2","asdf","gaenak")
//        val review3 = Review("1","3","qwer","gaenak")
//        val review4 = Review("1","4","zxcv","gaenak")
//        val review5 = Review("2","5","tyui","gaenak")
//        val review6 = Review("2","6","ghjk","gaenak")
//
//
//
//        if (idDetailReview=="1"){
//            val reviewList:ArrayList<Review> = arrayListOf<Review>(review1, review2, review3, review4)
//            reviewLD.value=reviewList
//        }
//        else if (idDetailReview=="2"){
//            val reviewList:ArrayList<Review> = arrayListOf<Review>(review5, review6)
//            reviewLD.value=reviewList
//        }


        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/veliciamp/B_160420067_UbayaKuliner/main/review"+idDetailReview.toString()+".json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Review>>() { }.type
                val result = Gson().fromJson<ArrayList<Review>>(it, sType)
                reviewLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
                reviewLoadErrorLD.value = false
//                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                reviewLoadErrorLD.value = true
                loadingLD.value = false
            })
//        tag itu kek idnya
        stringRequest.tag = TAG
        queue?.add(stringRequest)





    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}