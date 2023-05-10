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
import com.ubaya.ubayakuliner160420067.viewmodel.DetailReviewMenuViewModel


class MenuReviewFragment : Fragment() {
    private lateinit var viewModel: DetailReviewMenuViewModel
    private val reviewMenuListAdapter = ReviewMenuListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_review, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idDetailKuliner=""
        var idDetailMenu=""
        if(arguments!=null){
            idDetailKuliner=MenuReviewFragmentArgs.fromBundle(requireArguments()).idDetailKuliner
            idDetailMenu=MenuReviewFragmentArgs.fromBundle(requireArguments()).idDetailMenu
        }
        viewModel = ViewModelProvider(this).get(DetailReviewMenuViewModel::class.java)
        viewModel.refresh(idDetailKuliner,idDetailMenu)
        val recView = view?.findViewById<RecyclerView>(R.id.recViewDetailReviewMenu)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = reviewMenuListAdapter
        observeViewModel()
        val txtError = view?.findViewById<TextView>(R.id.txtErrorDetailReviewMenu)
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadDetailReviewMenu)
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.SwipeRefreshLayoutDetailReviewMenu)
        txtError.visibility = View.GONE
        progressLoad.visibility = View.GONE
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh(idDetailKuliner,idDetailMenu)
            refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.reviewMenuLD.observe(viewLifecycleOwner, Observer {
            reviewMenuListAdapter.updateReviewList(it)
        })

        viewModel.reviewMenuLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorDetailReviewMenu)

            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingReviewMenuLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewDetailReviewMenu)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadDetailReviewMenu)

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