package com.ubaya.ubayakuliner160420067.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakuliner160420067.model.Kuliner

class ListKulinerViewModel(application: Application): AndroidViewModel(application) {
    val kulinerLD = MutableLiveData<ArrayList<Kuliner>>()
    val kulinerLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
//        val kuliner1 =Kuliner("1","Ayam Geprek","Chicken","5718444778","https://randomuser.me/api/portraits/women/50.jpg")
//        val kuliner2 =Kuliner("2","Nasi Campur","Rice","3925444073","https://randomuser.me/api/portraits/women/50.jpg")
//        val kuliner3 =Kuliner("3","Mie Goreng","Noodle","6827808747","https://randomuser.me/api/portraits/women/50.jpg")
//        val kuliner4 =Kuliner("4","Batagor","Snack","6827808747","https://randomuser.me/api/portraits/women/50.jpg")
//
//        val kulinerList:ArrayList<Kuliner> = arrayListOf<Kuliner>(kuliner1, kuliner2, kuliner3, kuliner4)
//        kulinerLD.value = kulinerList
//        kulinerLoadErrorLD.value = false
//        loadingLD.value = false


        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/veliciamp/B_160420067_UbayaKuliner/main/kuliner.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Kuliner>>() { }.type
                val result = Gson().fromJson<ArrayList<Kuliner>>(it, sType)
                kulinerLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())

//                loadingLD.value = false
//                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                kulinerLoadErrorLD.value = true
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