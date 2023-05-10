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
import com.ubaya.ubayakuliner160420067.model.Menu

class DetailMenuViewModel(application: Application): AndroidViewModel(application) {
    val menuLD = MutableLiveData<Menu>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(idKuliner:String,idMenu:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/veliciamp/B_160420067_UbayaKuliner/main/kulmenu"+idKuliner.toString()+".json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Menu>>() { }.type
                val result = Gson().fromJson<ArrayList<Menu>>(it, sType)
                result.forEach{
                    if(it.idMenu.equals(idMenu)){
                        menuLD.value=it
                    }
                }

                Log.d("detailMenu", menuLD.value.toString())

//                loadingLD.value = false
//                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", menuLD.value.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)



    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}