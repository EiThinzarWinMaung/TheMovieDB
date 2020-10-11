package com.example.themoviedb.ui.cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.model.cast.CastX
import com.example.themoviedb.ui.details.DetailsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cast.view.*

class CastAdapter(var castList: List<CastX> = ArrayList<CastX>()): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    var cclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: DetailsFragment) {
        this.cclickListener = clickListener
    }

    inner class CastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var castX: CastX

        init {
            itemView.setOnClickListener(this)
        }

        fun bindCast(castX: CastX) {
            var imgURL: String = "http://image.tmdb.org/t/p/w500/"

            this.castX = castX
            itemView.txtCastName.text = castX.name
            itemView.txtCastCharacter.text = castX.character
            Picasso.get().load(imgURL + castX.profile_path).into(itemView.imgCastImage)
        }

        override fun onClick(p0: View?) {
            cclickListener?.onClick(castX)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return castList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        return holder.bindCast(castList[position])
    }

    fun updateCast(castList: List<CastX>) {
        this.castList = castList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(castX: CastX)
    }
}