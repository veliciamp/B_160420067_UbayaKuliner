package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.model.Promo
import com.ubaya.ubayakuliner160420067.model.Wallet
import com.ubaya.ubayakuliner160420067.util.loadImage

class WalletListAdapter(val walletList:ArrayList<Wallet>)
    : RecyclerView.Adapter<WalletListAdapter.WalletViewHolder>()  {
    class WalletViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wallet_list_item, parent, false)
        return WalletListAdapter.WalletViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val txtNamaWallet=holder.view.findViewById<TextView>(R.id.txtNamaWallet)
        val txtSaldoWallet=holder.view.findViewById<TextView>(R.id.txtSaldoWallet)
        val imageViewWallet=holder.view.findViewById<ImageView>(R.id.imageViewWallet)
        val progressBarWallet=holder.view.findViewById<ProgressBar>(R.id.progressBarWallet)

        txtNamaWallet.text=walletList[position].namaWallet
        txtSaldoWallet.text=walletList[position].saldoWallet
        imageViewWallet.loadImage(walletList[position].photo_url,progressBarWallet)
    }

    override fun getItemCount(): Int {return walletList.size

    }
    fun updateWalletList(newWalletList: ArrayList<Wallet>) {
        walletList.clear()
        walletList.addAll(newWalletList)
        notifyDataSetChanged()
    }
}