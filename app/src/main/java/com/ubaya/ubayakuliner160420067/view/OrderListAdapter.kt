package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.model.Orders
import com.ubaya.ubayakuliner160420067.util.loadImage
import kotlinx.android.synthetic.main.kuliner_list_item.view.*
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListAdapter(val orderList:ArrayList<Orders>)
    : RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>()  {
    class OrderViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_list_item, parent, false)
        return OrderViewHolder(view)

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val txtNamaOrder = holder.view.findViewById<TextView>(R.id.txtNamaOrder)
        val txtRestoOrder = holder.view.findViewById<TextView>(R.id.txtRestoOrder)
        val txtTanggalOrder=holder.view.findViewById<TextView>(R.id.txtTanggalOrder)
        val imageViewOrder=holder.view.findViewById<ImageView>(R.id.imageViewOrder)
        val progressBarOrder=holder.view.findViewById<ProgressBar>(R.id.progressBarOrder)

        txtNamaOrder.text = orderList[position].pesananMakanan
        txtRestoOrder.text = orderList[position].namaResto
        txtTanggalOrder.text = orderList[position].tanggal

        imageViewOrder.loadImage(orderList[position].photo_url, progressBarOrder)

    }
    override fun getItemCount(): Int {return orderList.size}
    fun updateOrderList(newOrderList: ArrayList<Orders>) {
        orderList.clear()
        orderList.addAll(newOrderList)
        notifyDataSetChanged()
    }

}