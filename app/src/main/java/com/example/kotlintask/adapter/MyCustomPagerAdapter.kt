package com.example.kotlintask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.example.kotlintask.R

class MyCustomPagerAdapter(var context: Context, var images: IntArray) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = layoutInflater.inflate(R.layout.image_slider_item, container, false)
        val imageView = itemView.findViewById<View>(R.id.idIVImage) as ImageView
        imageView.setImageResource(images[position])
        container.addView(itemView)

        //listening to image click
        imageView.setOnClickListener {
            Toast.makeText(
                context,
                "you clicked image " + (position + 1),
                Toast.LENGTH_LONG
            ).show()
        }
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}
