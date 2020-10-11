package com.example.themoviedb.ui.recommendation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.recommendation.Result
import com.example.themoviedb.ui.details.DetailsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recommendation.view.*

class RecommendationAdapter(var recommendationList: List<Result> = ArrayList<Result>()): RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    var mclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: DetailsFragment) {
        this.mclickListener = clickListener
    }

    inner class RecommendationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindRecommendation(result: Result) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.result = result
            itemView.movieNameRecommendation.text = result.title
            itemView.movieVoteRecommendation.text = result.vote_average.toString()
            Picasso.get().load(imgURL + result.poster_path).placeholder(R.drawable.movie_placeholder_1).into(itemView.movieImageRecommendation)
        }

        override fun onClick(p0: View?) {
            mclickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendation, parent, false)
        return RecommendationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recommendationList.size
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        return holder.bindRecommendation(recommendationList[position])
    }

    fun updateRecommendation(recommendationList: List<Result>) {
        this.recommendationList = recommendationList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}