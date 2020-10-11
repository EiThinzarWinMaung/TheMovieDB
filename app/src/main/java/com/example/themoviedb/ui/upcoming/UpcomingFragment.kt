package com.example.themoviedb.ui.upcoming

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
import com.example.themoviedb.model.upcoming.Result
import kotlinx.android.synthetic.main.fragment_upcoming.*

class UpcomingFragment : Fragment(), UpcomingAdapter.ClickListener {

    lateinit var upcomingAdapter: UpcomingAdapter
    lateinit var upcomingViewmodel: UpcomingViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingViewmodel = ViewModelProvider(this).get(UpcomingViewmodel::class.java)

        upcomingAdapter = UpcomingAdapter()

        recyclerviewUpcoming.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = upcomingAdapter
        }

        upcomingAdapter.setOnClickListener(this)
        observeViewModel()
    }

    private fun observeViewModel() {
        upcomingViewmodel.getUpcoming().observe(viewLifecycleOwner, Observer {movie ->
            upcomingAdapter.updateMovie(movie.results)
        })
    }

    override fun onResume() {
        super.onResume()
        upcomingViewmodel.loadUpcoming()
    }

    override fun onClick(result: Result) {
        var action = UpcomingFragmentDirections.actionUpcomingFragmentToDetailsFragment(result.id)
        findNavController().navigate(action)
    }
}