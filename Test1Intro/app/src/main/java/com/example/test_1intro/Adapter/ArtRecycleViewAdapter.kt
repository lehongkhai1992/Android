package com.example.test_1intro.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.s16mvvmcleanachitecture.R
import com.example.test_1intro.room.Art
import javax.inject.Inject

class ArtRecycleViewAdapter @Inject constructor(
    private val glide: RequestManager
) :RecyclerView.Adapter<ArtRecycleViewAdapter.ArtViewHolder>(){
    private val diffUtil = object : DiffUtil.ItemCallback<Art>(){
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }
    private val recycleListDiffer = AsyncListDiffer(this, diffUtil)
    var arts: List<Art>
        get() = recycleListDiffer.currentList
        set(value) = recycleListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return ArtViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arts.size
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.artImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.artRowNameText)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.artRowArtistNameText)
        val yearText = holder.itemView.findViewById<TextView>(R.id.artRowYearText)
        val art = arts[position]
        holder.itemView.apply {
            nameText.text = art.name
            artistNameText.text = art.artistName
            yearText.text = art.year
            glide.load(art.imageUrl).into(imageView)
        }

    }
    class ArtViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}