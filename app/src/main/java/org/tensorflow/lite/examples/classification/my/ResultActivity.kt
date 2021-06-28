package org.tensorflow.lite.examples.classification.my

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.iflower.R
import com.iflower.search.data.remote.FlowerAPI
import java.lang.Exception



class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val resultImageView = findViewById<ImageView>(R.id.resultImageView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        val resultIndex = intent.getStringExtra("result")
        if (resultIndex != null) {
            try {
                resultTextView.text = FlowerNames.getNameByIndex(resultIndex)
                resultImageView.load("${FlowerAPI.API_URL}/images/$resultIndex.jpg")
            } catch (e: Exception) {}
        }
    }

}
