package com.iflower.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.iflower.R
import com.iflower.account.AccountFragment
import com.iflower.account.data.model.User


class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_sign_up, container, false)

        root.findViewById<Button>(R.id.btnCreateAccount).setOnClickListener {
            val username = root.findViewById<EditText>(R.id.username_edit_text).text.toString()
            val password = root.findViewById<EditText>(R.id.password_edit_text).text.toString()
            if (username == "" || password == "") return@setOnClickListener
            val user = User()
            user.userName = username
            user.password = password
            (parentFragment as AccountFragment).createAccount(user)
        }

        return root
    }

}
