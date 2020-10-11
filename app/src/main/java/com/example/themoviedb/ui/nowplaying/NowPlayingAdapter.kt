package com.example.themoviedb.ui.nowplaying

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.nowplaying.Result
import com.example.themoviedb.ui.popular.PopularAdapter
import com.example.themoviedb.ui.popular.PopularFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_now_playing.view.*

class NowPlayingAdapter(var nowplayingList: List<Result> = ArrayList<Result>()): RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    var nclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: NowPlayingFragment) {
        this.nclickListener = clickListener
    }

    inner class NowPlayingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindNowPlaying(result: Result) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.result = result
            itemView.movieNameNowPlaying.text = result.title
            itemView.movieVoteNowPlaying.text = result.vote_average.toString()
            Picasso.get().load(imgURL + result.poster_path).placeholder(R.drawable.movie_placeholder_1).into(itemView.movieImageNowPlaying)
        }

        override fun onClick(view: View?) {
            nclickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing, parent, false)
        return NowPlayingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nowplayingList.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        return holder.bindNowPlaying(nowplayingList[position])
    }

    fun updateMovie(nowplayingList: List<Result>) {
        this.nowplayingList = nowplayingList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}