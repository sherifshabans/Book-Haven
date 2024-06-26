package com.elsharif.bookhaven.Adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elsharif.bookhaven.DetailsActivity
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.databinding.ItemCategoryBinding
import com.elsharif.bookhaven.utlis.loadOnline
class CategoryAdapter(val list: ArrayList<BooksModel>, val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BooksModel, context: Context) {
            binding.apply {
                mBookImage.loadOnline(model.image)
                mBookTitle.text = model.title
                mAuthorName.text = model.author
                mBookDes.text = model.description
                binding.root.setOnClickListener {
                    Intent().apply {
                        putExtra("book_model",model)
                        setClass(context, DetailsActivity::class.java)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context as Activity,
                            materialCardView,
                            materialCardView.transitionName
                        )
                        context.startActivity(this,options.toBundle())
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(
            model = list[position], context = context
        )
    }
}