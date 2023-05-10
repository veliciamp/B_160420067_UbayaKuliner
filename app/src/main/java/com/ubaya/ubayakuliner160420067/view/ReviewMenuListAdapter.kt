package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.model.ReviewMenu

class ReviewMenuListAdapter (val reviewMenuList:ArrayList<ReviewMenu>)
    : RecyclerView.Adapter<ReviewMenuListAdapter.ReviewMenuViewHolder>()   {
    class ReviewMenuViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewMenuListAdapter.ReviewMenuViewHolder  {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_menu_list_item, parent, false)
        return ReviewMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewMenuViewHolder, position: Int) {
        val txtIdReviewMenu = holder.view.findViewById<TextView>(R.id.txtIdReviewMenu)
        val txtReviewerNameMenu = holder.view.findViewById<TextView>(R.id.txtReviewerNameMenu)
        val txtReviewMenu = holder.view.findViewById<TextView>(R.id.txtReviewMenu)


        txtIdReviewMenu.text = reviewMenuList[position].idReview
        txtReviewerNameMenu.text = reviewMenuList[position].name
        txtReviewMenu.text = reviewMenuList[position].review
    }

    override fun getItemCount(): Int {
        return reviewMenuList.size

    }
    fun updateReviewList(newReviewMenuList: ArrayList<ReviewMenu>) {
        reviewMenuList.clear()
        reviewMenuList.addAll(newReviewMenuList)
        notifyDataSetChanged()
    }



}