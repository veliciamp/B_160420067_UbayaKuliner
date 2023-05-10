package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.model.Kuliner
import com.ubaya.ubayakuliner160420067.util.loadImage
import kotlinx.android.synthetic.main.kuliner_list_item.view.*

class KulinerListAdapter(val kulinerList:ArrayList<Kuliner>)
    :RecyclerView.Adapter<KulinerListAdapter.KulinerViewHolder>()  {
    class KulinerViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):KulinerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kuliner_list_item, parent, false)
        return KulinerViewHolder(view)

    }

    override fun onBindViewHolder(holder: KulinerViewHolder, position: Int) {
        holder.view.txtNamaKuliner.text = kulinerList[position].name
        holder.view.txtKategoriKuliner.text = kulinerList[position].category
        holder.view.btnDetailKuliner.setOnClickListener {
            val action = KulinerListFragmentDirections.actionDetailKuliner(kulinerList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageViewKuliner.loadImage(kulinerList[position].photoUrl, holder.view.progressBarKuliner)

    }
    override fun getItemCount(): Int {return kulinerList.size}
    fun updateKulinerList(newKulinerList: ArrayList<Kuliner>) {
        kulinerList.clear()
        kulinerList.addAll(newKulinerList)
        notifyDataSetChanged()
    }

}