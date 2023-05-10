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
import com.ubaya.ubayakuliner160420067.model.ReviewMenu

class DetailReviewMenuViewModel(application: Application): AndroidViewModel(application) {
    val reviewMenuLD = MutableLiveData<ArrayList<ReviewMenu>>()
    val reviewMenuLoadErrorLD = MutableLiveData<Boolean>()
    val loadingReviewMenuLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(idReviewKulinerKuliner:String,idReviewMenuKuliner:String){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/veliciamp/B_160420067_UbayaKuliner/main/revmenu"+idReviewKulinerKuliner.toString()+idReviewMenuKuliner.toString()+".json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<ReviewMenu>>() { }.type
                val result = Gson().fromJson<ArrayList<ReviewMenu>>(it, sType)
                reviewMenuLD.value = result
                loadingReviewMenuLD.value = false

                Log.d("showvoley", result.toString())
                reviewMenuLoadErrorLD.value = false
//                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                reviewMenuLoadErrorLD.value = true
                loadingReviewMenuLD.value = false
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