package com.rg.nytimes.activity.details


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rg.nytimes.databinding.AdapterImageItemBinding
import com.rg.nytimes.model.MediaMetaData

class ArticleImageAdapter(private val viewModel: ArticleDetailsViewModel) :
    RecyclerView.Adapter<ArticleImageAdapter.MediaViewHolder>() {

    private var items = emptyList<MediaMetaData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = AdapterImageItemBinding.inflate(layoutInflater, parent, false)
        return MediaViewHolder(itemBinding)
    }

    fun updateItemList(imageList: List<MediaMetaData>) {
        this.items = imageList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(items!![position])
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    class MediaViewHolder(private val binding: AdapterImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MediaMetaData?) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}