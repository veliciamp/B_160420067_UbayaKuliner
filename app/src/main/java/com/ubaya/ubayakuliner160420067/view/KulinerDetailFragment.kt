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
import com.ubaya.ubayakuliner160420067.viewmodel.DetailKulinerViewModel
import kotlinx.android.synthetic.main.fragment_kuliner_detail.*

class KulinerDetailFragment : Fragment() {

    private lateinit var viewModel: DetailKulinerViewModel

    var idKuliner=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kuliner_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            idKuliner=KulinerDetailFragmentArgs.fromBundle(requireArguments()).idKuliner
        }
        viewModel = ViewModelProvider(this).get(DetailKulinerViewModel::class.java)
        viewModel.fetch(idKuliner)
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.kulinerLD.observe(viewLifecycleOwner, Observer{
            val txtIdkulinerDetail= view?.findViewById<TextView>(R.id.txtIdKulinerDetail)
            val txtJudulKulinerDetail = view?.findViewById<TextView>(R.id.txtJudulKulinerDetail)
            val txtKategoriKulinerDetail = view?.findViewById<TextView>(R.id.txtKategoriKulinerDetail)
            val txtPhoneKulinerDetail=view?.findViewById<TextView>(R.id.txtPhoneKulinerDetail)
            val txtAlamatKulinerDetail=view?.findViewById<TextView>(R.id.txtAlamatKulinerDetail)
            val imageViewKulinerDetail=view?.findViewById<ImageView>(R.id.imageViewKulinerDetail)
            val btnShowReviewKulinerDetail=view?.findViewById<Button>(R.id.btnShowReviewKulinerDetail)
            val btnShowMenuKulinerDetail=view?.findViewById<Button>(R.id.btnShowMenuKulinerDetail)

            txtIdkulinerDetail?.setText(viewModel.kulinerLD.value?.id).toString()
            txtJudulKulinerDetail?.setText(viewModel.kulinerLD.value?.name).toString()
            txtKategoriKulinerDetail?.setText(viewModel.kulinerLD.value?.category).toString()
            txtPhoneKulinerDetail?.setText(viewModel.kulinerLD.value?.phone).toString()
            txtAlamatKulinerDetail?.setText(viewModel.kulinerLD.value?.alamat).toString()
            imageViewKulinerDetail?.loadImage(viewModel.kulinerLD.value?.photoUrl,progressBarKulinerDetail)
            btnShowReviewKulinerDetail?.setOnClickListener {
                val action = KulinerDetailFragmentDirections.actionDetailKulinerReview(idKuliner.toString())
                Navigation.findNavController(it).navigate(action)
                Log.d("showvoley", idKuliner)
            }
            btnShowMenuKulinerDetail?.setOnClickListener {
                val action = KulinerDetailFragmentDirections.actionDetailMenu(idKuliner.toString())
                Navigation.findNavController(it).navigate(action)
                Log.d("showvoley", idKuliner)
            }
        })
    }

}