package com.elsharif.bookhaven.Adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elsharif.bookhaven.DetailsActivity
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.databinding.ItemCategoryBinding
import com.elsharif.bookhaven.utlis.loadOnline

class CategoryAdapter (val list: ArrayList<BooksModel>,val context: Context):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(val binding: ItemCategoryBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(model: BooksModel,context: Context){
            binding.apply {
                model.apply {
                    /*Glide.with(itemView.context)
                        .load(image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .thumbnail(0.5f)
                        .into(mBookImage)
                    */
                     mBookImage.loadOnline(image)
                    // mBookImage.loadOnline2(image)
                    mBookTitle.text=title
                    mBookDes.text=description
                    mAuthourName.text= author

                    binding.root.setOnClickListener {
                        Intent().apply {
                            putExtra("book_model",model)
                            setClass(context, DetailsActivity::class.java)
                            val options= ActivityOptions.makeSceneTransitionAnimation(
                                context as Activity,
                                materialCardView,
                                materialCardView.transitionName)
                            context.startActivity(this,options.toBundle())
                        }
                    }

                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
    return CategoryViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val model=list[position]
        holder.bind(
            model = model, context = context
        )
    }
}