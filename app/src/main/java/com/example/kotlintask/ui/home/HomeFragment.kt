package com.example.kotlintask.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.kotlintask.Constants
import com.example.kotlintask.DTOs.LocationDTO
import com.example.kotlintask.HTTP
import com.example.kotlintask.R
import com.example.kotlintask.adapter.AdapterHomeAddress
import com.example.kotlintask.adapter.MyCustomPagerAdapter
import com.example.kotlintask.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class HomeFragment : Fragment() , OnMapReadyCallback {
    private lateinit var adapters : AdapterHomeAddress
    private lateinit var newRecyclerView : RecyclerView
    private lateinit var locationDTO: ArrayList<LocationDTO>

    private var handler: Handler? = null
    private var timer: Timer? = null
    private var viewPagerLenght = 0

    private lateinit var viewPager: ViewPager
    private var images = intArrayOf(R.drawable.box_image, R.drawable.box_fight, R.drawable.box)
    private var myCustomPagerAdapter: MyCustomPagerAdapter? = null

    private var dotsCount : Int = 0
    private var dots: Array<ImageView?> = arrayOfNulls(6)

    private var sliderPanel: LinearLayout? = null

    private var _binding: FragmentHomeBinding? = null
    private lateinit var gMap : GoogleMap
    private lateinit var openMapBtn : TextView

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

        imageSliderDots()

        viewPagerSlider()

        val layoutManager = LinearLayoutManager(context)
        newRecyclerView.layoutManager = layoutManager
        adapters = AdapterHomeAddress(locationDTO)
        newRecyclerView.adapter = adapters

        return root
    }

    private fun imageSliderDots() {
        myCustomPagerAdapter = MyCustomPagerAdapter(requireActivity().applicationContext, images)
        viewPager.adapter = myCustomPagerAdapter

        dotsCount = myCustomPagerAdapter!!.getCount()
        dots = arrayOfNulls(dotsCount)

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(context)
            dots[i]!!
                .setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity().applicationContext,
                        R.drawable.nonactive_dot
                    )
                )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderPanel!!.addView(dots[i], params)
        }

        dots[0]!!
            .setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity().applicationContext,
                    R.drawable.active_dot
                )
            )

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotsCount) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.nonactive_dot
                        )
                    )
                }
                dots[position]!!
                    .setImageDrawable(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.active_dot
                        )
                    )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }



    private fun locationDTOs() {
            locationDTO = getAddress()
    }

    private fun binding(){
        openMapBtn = binding.openMap
        newRecyclerView = binding.recyclerView
        viewPager = binding.imageView
        sliderPanel = binding.sliderDots
    }

    private fun getAddress(): ArrayList<LocationDTO> {
        return LocationDTO.GetValue( HTTP()
            .baseUrl(Constants.baseUrl + "getSports")
            .metode("GET")
            .connect()
            .content)!!
    }

    private fun viewPagerSlider() {
        handler = Handler()
        viewPagerLenght = binding.imageView.currentItem
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler!!.post {
                    viewPager.setCurrentItem(viewPagerLenght, true)
                    viewPagerLenght++
                    if (viewPagerLenght == myCustomPagerAdapter!!.count + 1) {
                        viewPagerLenght = 0
                    }
                }
            }
        }, 0, 5000)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}