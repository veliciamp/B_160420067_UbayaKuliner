package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R

import com.ubaya.ubayakuliner160420067.model.Promo
import com.ubaya.ubayakuliner160420067.util.loadImage
import kotlinx.android.synthetic.main.promo_list_item.view.*

class PromoListAdapter (val promoList:ArrayList<Promo>)
    : RecyclerView.Adapter<PromoListAdapter.PromoViewHolder>()  {
    class PromoViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoListAdapter.PromoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.promo_list_item, parent, false)
        return PromoListAdapter.PromoViewHolder(view)

    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val txtNamaPromo = holder.view.findViewById<TextView>(R.id.txtNamaPromo)
        val txtMakananPromo = holder.view.findViewById<TextView>(R.id.txtMakananPromo)
        val txtHargaAsliPromo=holder.view.findViewById<TextView>(R.id.txtHargaAsliPromo)
        val txtHargaAkhirPromo=holder.view.findViewById<TextView>(R.id.txtHargaAkhirPromo)
        val imageViewPromo=holder.view.findViewById<ImageView>(R.id.imageViewPromo)

        txtNamaPromo.text = promoList[position].namaPromo
        txtMakananPromo.text = promoList[position].makananPromo
        txtHargaAsliPromo.text = promoList[position].hargaAsli
        txtHargaAsliPromo.paintFlags = txtHargaAsliPromo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        txtHargaAkhirPromo.text = promoList[position].hargaDiskon

        imageViewPromo.loadImage(promoList[position].photo_url, holder.view.progressBarPromo)

    }

    override fun getItemCount(): Int {
        return promoList.size
    }
    fun updatePromoList(newPromoList: ArrayList<Promo>) {
        promoList.clear()
        promoList.addAll(newPromoList)
        notifyDataSetChanged()
    }


}