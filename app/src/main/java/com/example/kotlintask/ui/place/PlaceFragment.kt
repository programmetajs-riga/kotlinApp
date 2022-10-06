package com.example.kotlintask.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlintask.R
import com.example.kotlintask.databinding.FragmentPlaceBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class PlaceFragment : Fragment() , OnMapReadyCallback{

    private var _binding: FragmentPlaceBinding? = null

    private var mapSaveState : Int = 0

    private val binding get() = _binding!!
    private lateinit var gMap : GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if(mapSaveState == 0){
            gMap = googleMap
            val marker = LatLng(56.9600, 24.0997)
            gMap.addMarker(MarkerOptions().position(marker).title("Here"))
            gMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker , 12F))
            mapSaveState = 1
        }

    }
}