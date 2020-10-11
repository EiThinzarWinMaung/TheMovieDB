package com.example.themoviedb.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.model.cast.CastX
import com.example.themoviedb.model.recommendation.Result
import com.example.themoviedb.ui.biography.BiographyFragmentArgs
import com.example.themoviedb.ui.biography.BiographyViewmodel
import com.example.themoviedb.ui.cast.CastAdapter
import com.example.themoviedb.ui.cast.CastViewmodel
import com.example.themoviedb.ui.recommendation.RecommendationAdapter
import com.example.themoviedb.ui.recommendation.RecommendationViewmodel
import com.example.themoviedb.ui.reviews.ReviewAdapter
import com.example.themoviedb.ui.reviews.ReviewViewmodel
import com.example.themoviedb.ui.similar.SimilarAdapter
import com.example.themoviedb.ui.similar.SimilarViewmodel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_cast.*

class DetailsFragment : Fragment(), RecommendationAdapter.ClickListener,
    SimilarAdapter.ClickListener, CastAdapter.ClickListener {

    lateinit var detailsViewModel: DetailsViewModel
    lateinit var castAdapter: CastAdapter
    lateinit var castViewmodel: CastViewmodel
    lateinit var reviewAdapter: ReviewAdapter
    lateinit var reviewViewmodel: ReviewViewmodel
    lateinit var recommendationAdapter: RecommendationAdapter
    lateinit var recommendationViewmodel: RecommendationViewmodel
    lateinit var similarAdapter: SimilarAdapter
    lateinit var similarViewmodel: SimilarViewmodel

    var imgURL: String = "http://image.tmdb.org/t/p/w500/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Details
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        observeViewmodel()

        // Cast
        castAdapter = CastAdapter()
        castViewmodel = ViewModelProvider(this).get(CastViewmodel::class.java)

        recyclerviewCast.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = castAdapter
        }
        castAdapter.setOnClickListener(this)

        // Reviews
        reviewAdapter = ReviewAdapter()
        reviewViewmodel = ViewModelProvider(this).get(ReviewViewmodel::class.java)

        recyclerviewReviews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
        }

        // Recommendation
        recommendationAdapter = RecommendationAdapter()
        recommendationViewmodel = ViewModelProvider(this).get(RecommendationViewmodel::class.java)

        recyclerviewRecommendation.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendationAdapter
        }
        recommendationAdapter.setOnClickListener(this)

        // Similar
        similarAdapter = SimilarAdapter()
        similarViewmodel = ViewModelProvider(this).get(SimilarViewmodel::class.java)
        
        similarBlog.isVisible = true
        recyclerviewSimilar.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarAdapter
        }
        similarAdapter.setOnClickListener(this)
    }

    fun observeViewmodel() {

        detailsViewModel.getDetails().observe(viewLifecycleOwner, Observer { detail ->
            Picasso.get().load(imgURL + detail.backdrop_path).placeholder(R.drawable.movie_placeholder_2).into(imgDetailsImage)
            txtDetailsTitle.text = detail.title
            txtDetailsHomeLink.text = detail.homepage
            txtDetailsOverview.text = detail.overview
            txtDetailsPopularity.text = "Popularity : " + detail.popularity
            txtDetailsLanguage.text = "Language : " + detail.spoken_languages.get(0).name
            txtDetailsReleasedDate.text = detail.release_date
            txtDetailsGenres.text = "Genres : " + detail.genres.get(0).name
//            + " " + detail.genres.get(1).name
            txtProductionCompanyName.text = detail.production_companies.get(0).name
            txtProductionCompanyCountry.text = detail.production_countries.get(0).name
            if (detail.production_companies.get(0).logo_path != null) {
                Picasso.get().load(imgURL + detail.production_companies.get(0).logo_path).into(imgProductionCompanyLogo)
            }

            txtDetailsHomeLink.setOnClickListener {
                val link = detail.homepage
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(link)
                startActivity(i)
            }

            recommendationViewmodel.getRecommendation()
                .observe(viewLifecycleOwner, Observer { recommend ->
                    recommendationAdapter.updateRecommendation(recommend.results)
                })

            similarViewmodel.getSimilar().observe(viewLifecycleOwner, Observer { similar ->
                similarAdapter.updateMovie(similar.results)
            })

            castCard.setOnClickListener {
                detailsBlog.isVisible = false
                reviewBlog.isVisible = false
                castBlog.isVisible = true
                castViewmodel.getCast().observe(viewLifecycleOwner, Observer { casts ->
                    castAdapter.updateCast(casts.cast)
                })
            }

            reviewCard.setOnClickListener {
                detailsBlog.isVisible = false
                castBlog.isVisible = false
                reviewBlog.isVisible = true
                reviewViewmodel.getReviews().observe(viewLifecycleOwner, Observer { reviews ->
                    reviewAdapter.updateMovie(reviews.results)
                })
            }

            detailsCard.setOnClickListener {
                castBlog.isVisible = false
                reviewBlog.isVisible = false
                detailsBlog.isVisible = true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        var detailsArgs = arguments?.let {
            DetailsFragmentArgs.fromBundle(it)
        }

        var detailsId = detailsArgs?.detailsID

        detailsViewModel.loadDetails(detailsId!!)
        castViewmodel.loadCast(detailsId!!)
        reviewViewmodel.loadReviews(detailsId!!)
        recommendationViewmodel.loadRecommendation(detailsId!!)
        similarViewmodel.loadSimilar(detailsId!!)
    }

    // Recommendation
    override fun onClick(result: Result) {
        var action = DetailsFragmentDirections.actionDetailsFragmentSelf(result.id)
        findNavController().navigate(action)
    }

    // Similar
    override fun onClick(result: com.example.themoviedb.model.similar.Result) {
        var action = DetailsFragmentDirections.actionDetailsFragmentSelf(result.id)
        findNavController().navigate(action)
    }

    // Cast Biography
    override fun onClick(castX: CastX) {
        var action = DetailsFragmentDirections.actionDetailsFragmentToBiographyFragment(castX.id)
        findNavController().navigate(action)
    }
}
