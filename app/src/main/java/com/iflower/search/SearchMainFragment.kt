package com.iflower.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iflower.R
import com.iflower.search.data.model.Flower
import com.iflower.search.ui.FlowerFragment
import com.iflower.search.ui.SearchFragment



class SearchMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        childFragmentManager
                .beginTransaction()
                .replace(R.id.search_container, SearchFragment())
                .commit()
    }

    fun showFlower(flower: Flower) {
        childFragmentManager
                .beginTransaction()
                .replace(R.id.search_container, FlowerFragment(flower))
                .addToBackStack(null)
                .commit()
    }

}
