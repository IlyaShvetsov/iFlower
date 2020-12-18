package com.iflower.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.iflower.R
import com.iflower.account.AccountFragment
import com.iflower.account.data.remote.UserAPI



class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        root.findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            (parentFragment as AccountFragment).signUp()
        }

        root.findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            val username = root.findViewById<TextView>(R.id.username_edit_text).text.toString()
            val password = root.findViewById<TextView>(R.id.password_edit_text).text.toString()
            (parentFragment as AccountFragment).signIn(username, password)
        }

        root.findViewById<Button>(R.id.btnSignFacebook).setOnClickListener {
            Snackbar.make(root, "We will add this function later", Snackbar.LENGTH_LONG).show()
        }

        return root
    }

}
