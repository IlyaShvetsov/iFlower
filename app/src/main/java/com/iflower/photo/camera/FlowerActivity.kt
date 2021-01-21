package com.iflower.photo.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.iflower.R
import com.iflower.search.data.remote.FlowerAPI
import kotlin.concurrent.thread


class FlowerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_flower)

        thread {
            val flower = FlowerAPI.getFlowers()[0]
            runOnUiThread {
                findViewById<TextView>(R.id.flower_name).text = flower.name
                findViewById<TextView>(R.id.flower_description).text = flower.description
            }
        }
    }

}
