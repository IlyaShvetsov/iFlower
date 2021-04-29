package com.iflower.account.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.iflower.R
import com.iflower.account.AccountFragment
import com.iflower.account.data.local.UserStorage



class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        root.findViewById<TextView>(R.id.text_username).text = requireContext()
                .getSharedPreferences(UserStorage.prefName, Context.MODE_PRIVATE)
                .getString("username", "") ?: ""
        root.findViewById<ImageButton>(R.id.btn_logout).setOnClickListener {
            (parentFragment as AccountFragment).logout()
        }
        return root
    }

}
