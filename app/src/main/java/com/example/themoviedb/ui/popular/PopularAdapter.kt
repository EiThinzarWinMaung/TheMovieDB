package com.example.themoviedb.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.popular.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class PopularAdapter(var popularList: List<Result> = ArrayList<Result>()): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    var pclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: PopularFragment) {
        this.pclickListener = clickListener
    }

    inner class PopularViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindPopular(result: Result) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.result = result
            itemView.movieNamePopular.text = result.title
            itemView.movieVotePopular.text = result.vote_average.toString()
            Picasso.get().load(imgURL + result.poster_path).placeholder(R.drawable.movie_placeholder_1).into(itemView.movieImagePopular)
        }

        override fun onClick(view: View?) {
            pclickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        return holder.bindPopular(popularList[position])
    }

    fun updateMovie(popularList: List<Result>) {
        this.popularList = popularList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}