package com.iflower.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iflower.R
import com.iflower.search.SearchMainFragment
import com.iflower.search.data.model.Flower



class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: FlowerAdapter
    private var recView: RecyclerView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_search, container, false)
        recView     = root.findViewById(R.id.rec_view)
        progressBar = root.findViewById(R.id.progress_bar)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        adapter = FlowerAdapter { flower ->
            showFlower(flower)
        }
        recView?.layoutManager = LinearLayoutManager(requireContext())
        recView?.adapter = adapter

        searchViewModel.allFlowers.observe(this, {
            if (it.isEmpty()) {
                showProgress()
            } else {
                showData(it)
            }
        })

        searchViewModel.updateData()
    }

    private fun showData(data: List<Flower>) {
        adapter.setAll(data)
        progressBar?.visibility = View.INVISIBLE
        recView?.visibility = View.VISIBLE
    }

    private fun showProgress() {
        recView?.visibility = View.INVISIBLE
        progressBar?.visibility = View.VISIBLE
    }

    fun showFlower(flower: Flower) {
        (parentFragment as SearchMainFragment).showFlower(flower)
    }

}
