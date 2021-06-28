package com.iflower.photo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iflower.R
import org.tensorflow.lite.examples.classification.ClassifierActivity
import org.tensorflow.lite.examples.classification.my.TakePhotoActivity



class PhotoMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main_photo, container, false)

        root.findViewById<Button>(R.id.btn_real_time).setOnClickListener {
            startActivity(Intent(requireContext(), ClassifierActivity::class.java))
        }

        root.findViewById<Button>(R.id.btn_photo).setOnClickListener {
            startActivity(Intent(requireContext(), TakePhotoActivity::class.java))
        }

        return root
    }

}
