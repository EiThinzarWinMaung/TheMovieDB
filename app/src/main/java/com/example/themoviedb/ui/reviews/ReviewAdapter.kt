package com.example.themoviedb.ui.reviews

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.reviews.Result
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_reviews.view.*

class ReviewAdapter(var reviewList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindReview(result: Result) {
            itemView.txtReviewsAuthor.text = result.author
            itemView.txtReviewsContent.text = result.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_reviews, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        return holder.bindReview(reviewList[position])
    }

    fun updateMovie(reviewList: List<Result>) {
        this.reviewList = reviewList
        notifyDataSetChanged()
    }
}