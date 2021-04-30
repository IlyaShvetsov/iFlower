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



class PhotoMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main_photo, container, false)

        root.findViewById<Button>(R.id.btn_photo).setOnClickListener {
            val intent = Intent(requireContext(), ClassifierActivity::class.java)
            startActivity(intent)
        }

        return root
    }

}
