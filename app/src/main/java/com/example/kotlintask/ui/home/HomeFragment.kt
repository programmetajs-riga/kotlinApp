package com.example.kotlintask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintask.Constants
import com.example.kotlintask.DTOs.LocationDTO
import com.example.kotlintask.HTTP
import com.example.kotlintask.MainActivity
import com.example.kotlintask.R
import com.example.kotlintask.adapter.AdapterHomeAddress
import com.example.kotlintask.databinding.FragmentHomeBinding
import com.example.kotlintask.ui.place.PlaceFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class HomeFragment : Fragment() , OnMapReadyCallback {
    private lateinit var adapters : AdapterHomeAddress
    private lateinit var newRecyclerView : RecyclerView
    private lateinit var locationDTO: ArrayList<LocationDTO>

    private var _binding: FragmentHomeBinding? = null
    private lateinit var gMap : GoogleMap
    private lateinit var openMapBtn : TextView
    var f: PlaceFragment? = null

    private val binding get() = _binding!!

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        val marker = LatLng(56.9600, 24.0997)
        gMap.addMarker(MarkerOptions().position(marker).title("Here"))
        gMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker , 12F))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationDTOs()
        binding()
        val layoutManager = LinearLayoutManager(context)
        newRecyclerView.layoutManager = layoutManager
        adapters = AdapterHomeAddress(locationDTO)
        newRecyclerView.adapter = adapters



        openMapBtn.setOnClickListener{
            val onj = MainActivity()
            onj.navToPlace()
        }


        return root
    }

    private fun locationDTOs() {
            locationDTO = getAddress()
    }

    private fun binding(){
        openMapBtn = binding.openMap
        newRecyclerView = binding.recyclerView
    }

    private fun getAddress(): ArrayList<LocationDTO> {
        return LocationDTO.GetValue( HTTP()
            .baseUrl(Constants.baseUrl + "getSports")
            .metode("GET")
            .connect()
            .content)!!
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}