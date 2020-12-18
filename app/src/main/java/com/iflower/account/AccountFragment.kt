package com.iflower.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.iflower.R
import com.iflower.account.ui.AccountViewModel
import com.iflower.account.ui.LoginFragment
import com.iflower.account.ui.ProfileFragment
import com.iflower.account.ui.SignUpFragment



class AccountFragment : Fragment() {
    private lateinit var viewModel: AccountViewModel
    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_account, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        viewModel.loggedIn.observe(this, {
            val fragment = if (it) ProfileFragment() else LoginFragment()
            childFragmentManager
                    .beginTransaction()
                    .replace(R.id.account_container, fragment)
                    .commit()
        })
        viewModel.indicator.observe(this, {
            if (it > 0) {
                root?.let { it1 -> Snackbar.make(it1, "Incorrect login or password", Snackbar.LENGTH_LONG).show() }
            }
        })
    }

    fun signIn(userName: String, password: String) {
        viewModel.login(userName, password)
    }

    fun signUp() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.account_container, SignUpFragment())
            .addToBackStack(null)
            .commit()
    }

}
