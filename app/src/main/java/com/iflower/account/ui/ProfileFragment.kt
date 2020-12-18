package com.iflower.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iflower.R
import com.iflower.account.data.local.UserStorage



class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        root.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            UserStorage.logOut()
        }

        return root
    }

}
