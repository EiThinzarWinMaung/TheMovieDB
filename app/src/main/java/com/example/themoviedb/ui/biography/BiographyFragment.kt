package com.example.themoviedb.ui.biography

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_biography.*

class BiographyFragment : Fragment() {

    lateinit var biographyViewmodel: BiographyViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_biography, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var biographyArgs = arguments?.let {
            BiographyFragmentArgs.fromBundle(it)
        }

        var biographyId = biographyArgs?.personID

        biographyViewmodel = ViewModelProvider(this).get(BiographyViewmodel::class.java)
        biographyViewmodel.loadBiography(biographyId!!)

        observeViewModel()
    }

    private fun observeViewModel() {
        var imgURL: String = "http://image.tmdb.org/t/p/w500/"

        biographyViewmodel.getBiography().observe(viewLifecycleOwner, Observer { bio ->
            iconDeath.isVisible = false
            if(bio.name != null) {
                txtBioName.text = bio.name
            }
            txtBioDepartment.text = bio.known_for_department
            txtBioDOB.text = bio.birthday
            txtBioBirthPlace.text = bio.place_of_birth
            if (bio.deathday != null) {
                iconDeath.isVisible = true
                txtBioDOD.text = bio.deathday.toString()
            }
            txtBioPopularity.text = bio.popularity.toString()
            txtBioBiography.text = '"' + bio.biography + '"'
            Picasso.get().load(imgURL + bio.profile_path).into(imgBioImage)
        })
    }
}