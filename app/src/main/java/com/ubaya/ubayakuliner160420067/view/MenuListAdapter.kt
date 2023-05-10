package com.ubaya.ubayakuliner160420067.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.model.Menu
import com.ubaya.ubayakuliner160420067.util.loadImage
import kotlinx.android.synthetic.main.menu_list_item.view.*


class MenuListAdapter(val menuList:ArrayList<Menu>)
    : RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {
    class MenuViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListAdapter.MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_list_item, parent, false)
        return MenuListAdapter.MenuViewHolder(view)
    }
    override fun getItemCount(): Int {return menuList.size}
    fun updateMenuList(newMenuList: ArrayList<Menu>) {
        menuList.clear()
        menuList.addAll(newMenuList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MenuListAdapter.MenuViewHolder, position: Int) {
        val txtNamaMenu = holder.view.findViewById<TextView>(R.id.txtNamaMenu)
        val txtHargaMenu = holder.view.findViewById<TextView>(R.id.txtHargaMenu)
        val imageViewMenu=holder.view.findViewById<ImageView>(R.id.imageViewMenu)
        val btnDetailMenu=holder.view.findViewById<Button>(R.id.btnDetailMenu)
        btnDetailMenu.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuDetailMenu(menuList[position].idKuliner.toString(),menuList[position].idMenu.toString())

            Navigation.findNavController(it).navigate(action)
        }
        imageViewMenu.loadImage(menuList[position].photo_url, holder.view.progressBarMenu)

        txtNamaMenu.text = menuList[position].name
        txtHargaMenu.text = menuList[position].harga

    }
}