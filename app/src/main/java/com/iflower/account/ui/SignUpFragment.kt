package com.iflower.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.iflower.R
import com.iflower.account.AccountFragment
import com.iflower.account.data.model.User


class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_sign_up, container, false)

        root.findViewById<TextView>(R.id.txtTermService).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Term of Services")
                    .setMessage(resources.getString(R.string.termOfServices))
                    .setPositiveButton("Ok") { _, _ -> }
                    .show()
        }

        root.findViewById<Button>(R.id.btnCreateAccount).setOnClickListener {
            val username = root.findViewById<EditText>(R.id.username_edit_text).text.toString()
            val password = root.findViewById<EditText>(R.id.password_edit_text).text.toString()
            val confirmedPassword = root.findViewById<EditText>(R.id.confirm_password_edit_text).text.toString()
            if (username == "") {
                Snackbar.make(root, "Enter username", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password == "") {
                Snackbar.make(root, "Enter password", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password != confirmedPassword) {
                Snackbar.make(root, "Password mismatch", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val user = User()
            user.userName = username
            user.password = password
            (parentFragment as AccountFragment).createAccount(user)
        }

        return root
    }

}
