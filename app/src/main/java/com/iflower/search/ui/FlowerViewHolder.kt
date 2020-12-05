package com.iflower.search.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iflower.R
import com.iflower.search.data.model.Flower



class FlowerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//    private val onClick : (Flower) -> Unit
    private val flowerTextView: TextView = itemView.findViewById(R.id.flower_name)

    fun bind(flower: Flower) {
        flowerTextView.text = flower.name

//        itemView.setOnClickListener {
//            onClick(question)
//        }
    }

}
