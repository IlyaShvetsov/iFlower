package com.iflower.search.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iflower.R
import com.iflower.search.data.model.Flower
import com.iflower.search.data.remote.FlowerAPI
import kotlin.concurrent.thread


class FlowerFragment(private val flower: Flower) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_flower, container, false)
        root.findViewById<TextView>(R.id.flower_name).text = flower.name
        root.findViewById<TextView>(R.id.flower_description).text = flower.description

        thread {
            val image = FlowerAPI.getImage(flower.image!!)
            val bytes: ByteArray = image!!.bytes()
            Handler(Looper.getMainLooper()).post {
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                root.findViewById<ImageView>(R.id.flower_image).setImageBitmap(bitmap)
            }
        }

        return root
    }

}
