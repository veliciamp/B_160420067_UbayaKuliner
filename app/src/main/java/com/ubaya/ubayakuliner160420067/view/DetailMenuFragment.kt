package com.ubaya.ubayakuliner160420067.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner160420067.R
import com.ubaya.ubayakuliner160420067.util.loadImage
import com.ubaya.ubayakuliner160420067.viewmodel.DetailMenuViewModel
import kotlinx.android.synthetic.main.fragment_detail_menu.*


class DetailMenuFragment : Fragment() {
    private lateinit var viewModel: DetailMenuViewModel

    var idMenu=""
    var idKuliner=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            idKuliner=DetailMenuFragmentArgs.fromBundle(requireArguments()).idKulinerKuliner
            idMenu=DetailMenuFragmentArgs.fromBundle(requireArguments()).idMenuKuliner
        }
        viewModel = ViewModelProvider(this).get(DetailMenuViewModel::class.java)
        viewModel.fetch(idKuliner,idMenu)
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.menuLD.observe(viewLifecycleOwner, Observer{
            val txtIdDetailMenu= view?.findViewById<TextView>(R.id.txtIdMenuDetail)
            val txtJudulMenuDetail = view?.findViewById<TextView>(R.id.txtJudulMenuDetail)
            val txtHargaMenuDetail = view?.findViewById<TextView>(R.id.txtHargaMenuDetail)
            val txtKeteranganMenuDetail=view?.findViewById<TextView>(R.id.txtKeteranganMenuDetail)
            val btnShowReviewMenuDetail=view?.findViewById<Button>(R.id.btnShowReviewMenuDetail)
            val imageViewMenuDetail=view?.findViewById<ImageView>(R.id.imageViewMenuDetail)

            txtIdDetailMenu?.setText(viewModel.menuLD.value?.idMenu).toString()
            txtJudulMenuDetail?.setText(viewModel.menuLD.value?.name).toString()
            txtHargaMenuDetail?.setText(viewModel.menuLD.value?.harga).toString()
            txtKeteranganMenuDetail?.setText(viewModel.menuLD.value?.keterangan).toString()

            imageViewMenuDetail?.loadImage(viewModel.menuLD.value?.photo_url,progressBarMenuDetail)
            btnShowReviewMenuDetail?.setOnClickListener {
                val action = DetailMenuFragmentDirections.actionMenuDetailMenuReview(idKuliner.toString(),idMenu.toString())
                Navigation.findNavController(it).navigate(action)
                Log.d("showvoley", idMenu)
            }

        })
    }


}