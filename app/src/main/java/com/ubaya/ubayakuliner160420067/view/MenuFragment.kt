package com.ubaya.ubayakuliner160420067.view

import android.os.Bundle
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
import com.ubaya.ubayakuliner160420067.viewmodel.ListMenuViewModel


class MenuFragment : Fragment() {
    private lateinit var viewModel: ListMenuViewModel
    private val menuListAdapter = MenuListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idKuliner=""
        if(arguments!=null){
            idKuliner=MenuFragmentArgs.fromBundle(requireArguments()).idKuliner
        }
        viewModel = ViewModelProvider(this).get(ListMenuViewModel::class.java)
        viewModel.refresh(idKuliner)
        val recView = view?.findViewById<RecyclerView>(R.id.recViewMenu)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = menuListAdapter
        observeViewModel()
        val txtError = view?.findViewById<TextView>(R.id.txtErrorMenu)
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadMenu)
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.SwipeRefreshLayoutMenu)
        txtError.visibility = View.GONE
        progressLoad.visibility = View.GONE
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh(idKuliner)
            refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.menuLD.observe(viewLifecycleOwner, Observer {
            menuListAdapter.updateMenuList(it)
        })

        viewModel.menuLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorMenu)

            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewMenu)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadMenu)

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