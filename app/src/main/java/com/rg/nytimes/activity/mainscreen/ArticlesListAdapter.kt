package com.rg.nytimes.activity.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rg.nytimes.databinding.AdapterArticleItemBinding
import com.rg.nytimes.model.ArticleInfo

class ArticlesListAdapter(val articleViewModel: ArticleListViewModel) : RecyclerView.Adapter<ArticlesListAdapter.BaseViewHolder<*>>() {

    private var items = emptyList<ArticleInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val adapterArticleItemBinding = AdapterArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(adapterArticleItemBinding)
    }

    class CategoryHolder(adapterArticleItemBinding: AdapterArticleItemBinding) : BaseViewHolder<ArticleInfo>(adapterArticleItemBinding.getRoot()) {
        var itemBinding: AdapterArticleItemBinding = adapterArticleItemBinding

        override fun bind(obj: ArticleInfo, articleViewModel: ArticleListViewModel, position: Int) {
            itemBinding.item = obj
            itemBinding.viewModel = articleViewModel
            itemBinding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItemList(documentsDTOs: List<ArticleInfo>) {
        this.items = documentsDTOs
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = items[position]
        when (holder) {
            is CategoryHolder -> holder.bind(element, articleViewModel, position)
            else -> throw IllegalArgumentException()
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T, articleViewModel: ArticleListViewModel, position: Int)
    }
}