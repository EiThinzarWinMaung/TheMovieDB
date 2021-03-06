package com.example.themoviedb.ui.toprated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.model.Result
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(), TopRatedAdapter.ClickListener {

    lateinit var topratedAdapter: TopRatedAdapter
    lateinit var topRatedViewmodel: TopRatedViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedViewmodel = ViewModelProvider(this).get(TopRatedViewmodel::class.java)

        topratedAdapter = TopRatedAdapter()

        recyclerviewTopRated.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = topratedAdapter
        }

        topratedAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        topRatedViewmodel.getTopRated().observe(viewLifecycleOwner, Observer { list ->
            topratedAdapter.updateTopRatedList(list.results)
        })
    }

    override fun onResume() {
        super.onResume()
        topRatedViewmodel.loadTopRated()
    }

    override fun onClick(result: Result) {
        var action = TopRatedFragmentDirections.actionNavTopratedToDetailsFragment(result.id)
        findNavController().navigate(action)
    }
}