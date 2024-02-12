package com.example.nadbitov_fintech.presentation.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nadbitov_fintech.data.api.FilmApi
import com.example.nadbitov_fintech.databinding.ViewHolderListBinding
import com.example.nadbitov_fintech.presentation.MovieClickListener

class DetailAdapter(private val onItemClick: (FilmApi) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    private var items: List<FilmApi> = emptyList()
    private val listener: MovieClickListener? = null

    fun setData(data: List<FilmApi>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderListBinding.inflate(inflater, parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
            listener?.onMovieClicked(currentItem, currentItem.filmId)
            Log.d("movie", "${currentItem.filmId}")
        }
    }

    override fun getItemCount(): Int = items.size

    class DetailViewHolder(private val binding: ViewHolderListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilmApi) {
            binding.apply {
                binding.filmName.text = item.nameRu
                binding.filmGenre.text = item.genres.first().genre
                binding.year.text = " (${item.year})"
                Glide.with(banner)
                    .load(item.posterUrlPreview)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .into(banner)
            }
        }
    }
}