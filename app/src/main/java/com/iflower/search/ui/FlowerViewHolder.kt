package com.iflower.search.ui

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iflower.R
import com.iflower.search.data.model.Flower
import com.iflower.search.data.remote.FlowerAPI
import kotlin.concurrent.thread


class FlowerViewHolder(itemView: View, private val onClick: (Flower) -> Unit): RecyclerView.ViewHolder(itemView) {
    private val flowerTextView: TextView = itemView.findViewById(R.id.flower_name)
    private val flowerImageView: ImageView = itemView.findViewById(R.id.flower_image)

    fun bind(flower: Flower) {
        flowerTextView.text = flower.name

        if (flower.image != null) {
            thread {
                val image = FlowerAPI.getImage(flower.image!!)
                val bytes: ByteArray = image!!.bytes()
                Handler(Looper.getMainLooper()).post {
                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    flowerImageView.setImageBitmap(bitmap)
                }
            }
        }

        itemView.setOnClickListener {
            onClick(flower)
        }
    }

}
