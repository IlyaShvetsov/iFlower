package com.iflower.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.iflower.R



class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        root.findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            (parentFragment as AccountFragment).onSignUp()
        }

        root.findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            if (true) {
                (parentFragment as AccountFragment).openProfile()
            } else {
                Snackbar.make(root, "Incorrect login or password", Snackbar.LENGTH_LONG).show()
            }
        }

        root.findViewById<Button>(R.id.btnSignFacebook).setOnClickListener {
            Snackbar.make(root, "We will add this function later", Snackbar.LENGTH_LONG).show()
        }

        return root
    }

}
