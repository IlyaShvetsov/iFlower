package com.iflower.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iflower.R



class AccountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        childFragmentManager
            .beginTransaction()
            .replace(R.id.account_container, LoginFragment())
            .commit()
    }

    fun onSignUp() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.account_container, SignUpFragment())
            .addToBackStack(null)
            .commit()
    }

    fun openProfile() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.account_container, ProfileFragment())
            .commit()
    }

}
