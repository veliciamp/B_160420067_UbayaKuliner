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
import com.ubaya.ubayakuliner160420067.viewmodel.ListKulinerViewModel


class KulinerListFragment : Fragment() {
    private lateinit var viewModel:ListKulinerViewModel
    private val kulinerListAdapter = KulinerListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListKulinerViewModel::class.java)
        viewModel.refresh()
        val recView = view?.findViewById<RecyclerView>(R.id.recViewKuliner)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = kulinerListAdapter
        observeViewModel()
        val txtError = view?.findViewById<TextView>(R.id.txtErrorKuliner)
        txtError.visibility = View.GONE
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadKuliner)
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutKuliner)
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
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer {
        kulinerListAdapter.updateKulinerList(it)
    })
        viewModel.kulinerLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorKuliner)

            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewKuliner)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadKuliner)

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