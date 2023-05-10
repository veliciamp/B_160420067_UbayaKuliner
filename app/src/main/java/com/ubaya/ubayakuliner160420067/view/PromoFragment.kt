package com.ubaya.ubayakuliner160420067.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.viewmodel.ListOrdersViewModel
import com.ubaya.ubayakuliner160420067.viewmodel.ListPromosViewModel

class PromoFragment : Fragment() {
    private lateinit var viewModel: ListPromosViewModel
    private val promoListAdapter = PromoListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListPromosViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewPromo)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = promoListAdapter
        observeViewModel()
        val txtError = view?.findViewById<TextView>(R.id.txtErrorPromo)
        txtError.visibility = View.GONE
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadPromo)
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.SwipeRefreshLayoutPromo)
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            Log.d("coba", txtError.visibility.toString())
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
    fun observeViewModel() {
        viewModel.promoLD.observe(viewLifecycleOwner, Observer {
            promoListAdapter.updatePromoList(it)
        })
        viewModel.promoLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorPromo)

            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewPromo)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadPromo)

            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}