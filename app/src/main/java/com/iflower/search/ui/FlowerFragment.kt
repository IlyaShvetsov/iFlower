package com.iflower.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.iflower.R
import com.iflower.search.data.model.Flower
import com.iflower.search.data.remote.FlowerAPI


class FlowerFragment(private val flower: Flower) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_flower, container, false)
        root.findViewById<TextView>(R.id.flower_name).text = flower.name
        root.findViewById<TextView>(R.id.flower_description).text = flower.description
        root.findViewById<ImageView>(R.id.flower_image)
                .load("${FlowerAPI.API_URL}/images/${flower.image}")
        return root
    }

}
