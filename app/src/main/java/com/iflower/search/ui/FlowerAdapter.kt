package com.iflower.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iflower.R
import com.iflower.search.data.model.Flower
import com.iflower.search.ui.FlowerViewHolder



class FlowerAdapter() : RecyclerView.Adapter<FlowerViewHolder>() {
//    private val onClick : (Flower) -> Unit
    private val dataList: ArrayList<Flower> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.flower_item, parent, false)
        return FlowerViewHolder(view) // , onClick
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setAll(questions: List<Flower>) {
        dataList.clear()
        dataList.addAll(questions)
        notifyDataSetChanged()
    }

}
