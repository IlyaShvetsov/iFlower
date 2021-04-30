package com.iflower.search.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.iflower.R
import com.iflower.search.data.model.Flower
import com.iflower.search.data.remote.FlowerAPI


class FlowerViewHolder(itemView: View, private val onClick: (Flower) -> Unit): RecyclerView.ViewHolder(itemView) {
    private val flowerTextView: TextView = itemView.findViewById(R.id.flower_name)
    private val flowerImageView: ImageView = itemView.findViewById(R.id.flower_image)

    fun bind(flower: Flower) {
        flowerTextView.text = flower.name

        if (flower.image != null) {
            flowerImageView.load("${FlowerAPI.API_URL}/images/${flower.image}")
        }

        itemView.setOnClickListener {
            onClick(flower)
        }
    }

}
