package com.example.themoviedb.ui.toprated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_top_rated.view.*

class TopRatedAdapter(var topratedList: List<Result> = ArrayList<Result>()): RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    var mclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: TopRatedFragment) {
        this.mclickListener = clickListener
    }

    inner class TopRatedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindTopRated(result: Result) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.result = result
            itemView.movieNameTopRated.text = result.title
            itemView.movieVoteTopRated.text = result.vote_average.toString()
            Picasso.get().load(imgURL + result.poster_path).placeholder(R.drawable.movie_placeholder_1).into(itemView.movieImageTopRated)
        }

        override fun onClick(view: View?) {
            mclickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_rated, parent, false)
        return TopRatedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topratedList.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        return holder.bindTopRated(topratedList[position])
    }

    fun updateTopRatedList(topratedList: List<Result>) {
        this.topratedList = topratedList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}