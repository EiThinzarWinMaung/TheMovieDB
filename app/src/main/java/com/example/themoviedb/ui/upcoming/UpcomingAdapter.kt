package com.example.themoviedb.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.upcoming.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_upcoming.view.*

class UpcomingAdapter(var upcomingList: List<Result> = ArrayList<Result>()): RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    var mclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: UpcomingFragment) {
        this.mclickListener = clickListener
    }

    inner class UpcomingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindUpcoming(result: Result) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.result = result
            itemView.movieNameUpcoming.text = result.title
            itemView.movieVoteUpcoming.text = result.vote_average.toString()
            Picasso.get().load(imgURL + result.poster_path).placeholder(R.drawable.movie_placeholder_1).into(itemView.movieImageUpcoming)
        }

        override fun onClick(p0: View?) {
            mclickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        return holder.bindUpcoming(upcomingList[position])
    }

    fun updateMovie(upcomingList: List<Result>) {
        this.upcomingList = upcomingList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}