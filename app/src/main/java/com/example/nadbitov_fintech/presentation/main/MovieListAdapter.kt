package com.example.nadbitov_fintech.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nadbitov_fintech.databinding.ViewHolderListBinding

class MovieListAdapter(
    private val onItemClick: (FilmUi) -> Unit,
    private val onLongItemClick: (FilmUi) -> Unit,
) : ListAdapter<FilmUi, MovieListAdapter.ViewHolder>(ItemDiffUtil()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onItemClick, onLongItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val viewBinding: ViewHolderListBinding,
        private val onItemClick: (FilmUi) -> Unit,
        private val onLongItemClick: (FilmUi) -> Unit,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: FilmUi) {
            viewBinding.apply {
                filmName.text = item.nameRu
                filmGenre.text = item.genres.first().name
                year.text = item.year

                root.setOnClickListener {
                    onItemClick(item)
                }

                root.setOnLongClickListener {
                    onLongItemClick(item)
                    true
                }

                star.isVisible = item.isFavourite

                Glide.with(banner)
                    .load(item.posterUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(CORNER_RADIUS)))
                    .into(banner)
            }
        }

        private companion object {
            const val CORNER_RADIUS = 16
        }
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<FilmUi>() {

        override fun areItemsTheSame(oldItem: FilmUi, newItem: FilmUi): Boolean {
            return oldItem.nameRu == newItem.nameRu
        }

        override fun areContentsTheSame(oldItem: FilmUi, newItem: FilmUi): Boolean {
            return oldItem == newItem
        }
    }
}