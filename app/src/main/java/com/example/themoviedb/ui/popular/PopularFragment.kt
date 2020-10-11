package com.example.themoviedb.ui.popular

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
import com.example.themoviedb.model.popular.Result
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(), PopularAdapter.ClickListener {

    lateinit var popularAdapter: PopularAdapter
    lateinit var popularViewmodel: PopularViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularViewmodel = ViewModelProvider(this).get(PopularViewmodel::class.java)

        popularAdapter = PopularAdapter()

        recyclerviewPopular.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = popularAdapter
        }

        popularAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        popularViewmodel.getPopular().observe(viewLifecycleOwner, Observer { movie ->
            popularAdapter.updateMovie(movie.results)
        })
    }

    override fun onResume() {
        super.onResume()
        popularViewmodel.loadPopular()
    }

    override fun onClick(result: Result) {
        var action = PopularFragmentDirections.actionNavPopularToDetailsFragment(result.id)
        findNavController().navigate(action)
    }
}