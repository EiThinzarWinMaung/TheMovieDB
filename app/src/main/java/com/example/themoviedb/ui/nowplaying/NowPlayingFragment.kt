package com.example.themoviedb.ui.nowplaying

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
import com.example.themoviedb.model.nowplaying.Result
import kotlinx.android.synthetic.main.fragment_now_playing.*

class NowPlayingFragment : Fragment(), NowPlayingAdapter.ClickListener {

    lateinit var nowPlayingAdapter: NowPlayingAdapter
    lateinit var nowPlayingViewmodel: NowPlayingViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingViewmodel = ViewModelProvider(this).get(NowPlayingViewmodel::class.java)

        nowPlayingAdapter = NowPlayingAdapter()

        recyclerviewNowPlaying.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = nowPlayingAdapter
        }

        nowPlayingAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        nowPlayingViewmodel.getNowPlaying().observe(viewLifecycleOwner, Observer { movie ->
            nowPlayingAdapter.updateMovie(movie.results)
        })
    }

    override fun onResume() {
        super.onResume()
        nowPlayingViewmodel.loadNowPlaying()
    }

    override fun onClick(result: Result) {
        var action = NowPlayingFragmentDirections.actionNavNowplayingToDetailsFragment(result.id)
        findNavController().navigate(action)
    }
}