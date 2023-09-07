package com.example.assignmentproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentproject.databinding.ActivityMainBinding
import com.example.assignmentproject.databinding.ItemRowMangaBinding

class ListComicAdapter(private val listComic: ArrayList<Comic>) : RecyclerView.Adapter<ListComicAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowMangaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ListComicAdapter.ListViewHolder {
        val binding = ItemRowMangaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListComicAdapter.ListViewHolder, position: Int) {
        val (name, description, photo) = listComic[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listComic[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listComic.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Comic)
    }


}