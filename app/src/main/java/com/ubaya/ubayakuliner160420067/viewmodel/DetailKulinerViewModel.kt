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

class DetailKulinerViewModel(application: Application): AndroidViewModel(application){

    val kulinerLD = MutableLiveData<Kuliner>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun fetch(idKuliner:String){
//        val kuliner1 =Kuliner("1","Ayam Geprek","Chicken","5718444778","https://randomuser.me/api/portraits/women/50.jpg")
//        val kuliner2 =Kuliner("2","Nasi Campur","Rice","3925444073","https://randomuser.me/api/portraits/women/50.jpg")
//        val kuliner3 =Kuliner("3","Mie Goreng","Noodle","6827808747","https://randomuser.me/api/portraits/women/50.jpg")
//        val kuliner4 =Kuliner("4","Batagor","Snack","6827808747","https://randomuser.me/api/portraits/women/50.jpg")
//
//        if (idKuliner==kuliner1.id){
//            kulinerLD.value=kuliner1
//        }
//        else if (idKuliner==kuliner2.id){
//            kulinerLD.value=kuliner2
//        }
//        else if (idKuliner==kuliner3.id){
//            kulinerLD.value=kuliner3
//        }
//        else if (idKuliner==kuliner4.id){
//            kulinerLD.value=kuliner4
//        }

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/veliciamp/B_160420067_UbayaKuliner/main/kuliner.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Kuliner>>() { }.type
                val result = Gson().fromJson<ArrayList<Kuliner>>(it, sType)
                result.forEach{
                    if(it.id.equals(idKuliner)){
                        kulinerLD.value=it
                    }
                }

                Log.d("detailKuliner", kulinerLD.value.toString())

//                loadingLD.value = false
//                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", kulinerLD.value.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)



    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}