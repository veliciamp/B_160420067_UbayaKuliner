package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.model.Review

class ReviewListAdapter(val reviewList:ArrayList<Review>)
: RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>()   {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListAdapter.ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item, parent, false)
        return ReviewViewHolder(view)

    }
    override fun getItemCount(): Int {return reviewList.size}
    fun updateReviewList(newReviewList: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val txtIdReview = holder.view.findViewById<TextView>(R.id.txtIdReview)
        val txtReviewerName = holder.view.findViewById<TextView>(R.id.txtReviewerName)
        val txtReview = holder.view.findViewById<TextView>(R.id.txtReview)


        txtIdReview.text = reviewList[position].idReview
        txtReviewerName.text = reviewList[position].name
        txtReview.text = reviewList[position].review

    }

}

