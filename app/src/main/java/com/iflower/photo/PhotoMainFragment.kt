package com.iflower.photo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.iflower.R
import com.iflower.account.data.local.UserStorage
import org.tensorflow.lite.examples.classification.ClassifierActivity



class PhotoMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main_photo, container, false)

        root.findViewById<Button>(R.id.btn_photo).setOnClickListener {
//            val loggedIn = requireContext()
//                    .getSharedPreferences(UserStorage.prefName, Context.MODE_PRIVATE)
//                    .getBoolean("loggedIn", false)
//            if (loggedIn) {
                startActivity(Intent(requireContext(), ClassifierActivity::class.java))
//            } else {
//                Snackbar.make(root, "Пожалуйста, авторизуйтесь в приложении", Snackbar.LENGTH_LONG).show()
//            }
        }

        return root
    }

}
