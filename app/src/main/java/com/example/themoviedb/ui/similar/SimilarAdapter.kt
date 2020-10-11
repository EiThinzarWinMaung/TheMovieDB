package com.example.themoviedb.ui.similar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.similar.Result
import com.example.themoviedb.ui.details.DetailsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_similar.view.*

class SimilarAdapter(var similarList: List<Result> = ArrayList<Result>()): RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {

    var sclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: DetailsFragment) {
        this.sclickListener = clickListener
    }

    inner class SimilarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindSimilar(result: Result) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.result = result
            itemView.movieNameSimilar.text = result.title
            itemView.movieVoteSimilar.text = result.vote_average.toString()
            Picasso.get().load(imgURL + result.poster_path).placeholder(R.drawable.movie_placeholder_1).into(itemView.movieImageSimilar)
        }

        override fun onClick(p0: View?) {
            sclickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_similar, parent, false)
        return SimilarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return similarList.size
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        return holder.bindSimilar(similarList[position])
    }

    fun updateMovie(similarList: List<Result>) {
        this.similarList = similarList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}