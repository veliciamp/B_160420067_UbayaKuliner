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
import com.ubaya.ubayakuliner160420067.viewmodel.ListReviewViewModel

class DetailReviewFragment : Fragment() {
    private lateinit var viewModel: ListReviewViewModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_review, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idDetailReview=""
        if(arguments!=null){
            idDetailReview=DetailReviewFragmentArgs.fromBundle(requireArguments()).idDetailReview
        }
        viewModel = ViewModelProvider(this).get(ListReviewViewModel::class.java)
        viewModel.refresh(idDetailReview)
        val recView = view?.findViewById<RecyclerView>(R.id.recViewDetailReview)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = reviewListAdapter
        observeViewModel()
        val txtError = view?.findViewById<TextView>(R.id.txtErrorDetailReview)
        val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadDetailReview)
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.SwipeRefreshLayoutDetailReview)
        txtError.visibility = View.GONE
        progressLoad.visibility = View.GONE
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh(idDetailReview)
            refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            reviewListAdapter.updateReviewList(it)
        })

        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtErrorDetailReview)

            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewDetailReview)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoadDetailReview)

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