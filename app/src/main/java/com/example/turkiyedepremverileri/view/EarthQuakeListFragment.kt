package com.example.turkiyedepremverileri.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.turkiyedepremverileri.R
import com.example.turkiyedepremverileri.adapter.EarthQuakeListAdapter
import com.example.turkiyedepremverileri.databinding.FragmentEarthQuakeListBinding
import com.example.turkiyedepremverileri.model.EarthQuakeListResponceModel
import com.example.turkiyedepremverileri.viewmodel.EarthQuakeListViewModel

class EarthQuakeListFragment : Fragment() {

    private lateinit var binding: FragmentEarthQuakeListBinding
    private var earthQuakeListAdapter = EarthQuakeListAdapter()
    private lateinit var earthQuakeListViewModel: EarthQuakeListViewModel
    private var tempEarthQuakeList: ArrayList<EarthQuakeListResponceModel.Result> = arrayListOf()
    private var filteredQuakeList: ArrayList<EarthQuakeListResponceModel.Result> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        earthQuakeListViewModel = ViewModelProvider(this).get(EarthQuakeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEarthQuakeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            earthQuakeListViewModel.getEarthQuakeList()


            earthQuakeListViewModel.earthQuakeListLiveData.observe(viewLifecycleOwner){
                it.result?.let {
                    tempEarthQuakeList = it
                    rvEarthQuekaList.adapter = earthQuakeListAdapter
                    earthQuakeListAdapter.setEarthQuakeList(it)
                }
            }
            swipe.setOnRefreshListener {
                swipe.isRefreshing = false
                progresBarList.visibility = View.VISIBLE
                earthQuakeListViewModel.getEarthQuakeList()
            }
        }
    }
}