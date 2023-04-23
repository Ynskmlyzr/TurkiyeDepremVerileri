package com.example.turkiyedepremverileri.view

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.turkiyedepremverileri.R
import com.example.turkiyedepremverileri.databinding.FragmentMapBinding
import com.example.turkiyedepremverileri.model.LatLongData
import com.example.turkiyedepremverileri.viewmodel.EarthQuakeListViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    val latLongList: ArrayList<LatLongData> = arrayListOf()
    private lateinit var earthQuakeListViewModel: EarthQuakeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        earthQuakeListViewModel = ViewModelProvider(this).get(EarthQuakeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        earthQuakeListViewModel.getEarthQuakeList()

        earthQuakeListViewModel.earthQuakeListLiveData.observe(viewLifecycleOwner){ response->
            response?.result?.let {
                it.forEach {
                    latLongList.add(LatLongData(it.title, it.geojson.coordinates.get(1), it.geojson.coordinates.get(0), it.mag))
                    latLongList.size
                }
            }
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        latLongList.forEach { latLong->
            val sydney = LatLng(latLong.lat ?: 0.0, 	latLong.long ?: 0.0)
            latLong.mag?.let {
                if (it < 3){
                    googleMap.addMarker(MarkerOptions().position(sydney).title(latLong.cityName + " - " + latLong.mag).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
                }else if(it < 4){
                    googleMap.addMarker(MarkerOptions().position(sydney).title(latLong.cityName + " - " + latLong.mag).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)))
                }else{
                    googleMap.addMarker(MarkerOptions().position(sydney).title(latLong.cityName + " - " + latLong.mag))
                }
            }
            binding.ivZoomOut.setOnClickListener {
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(googleMap.getCameraPosition().zoom + 0.5f));
            }
            binding.ivZoomIn.setOnClickListener {
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(googleMap.getCameraPosition().zoom - 0.5f));
            }
        }
        googleMap.setMinZoomPreference(5f)
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(LatLng(38.626995,34.719975)))
    }

}