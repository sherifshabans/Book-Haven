package com.elsharif.bookhaven.Adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.elsharif.bookhaven.DetailsActivity
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.Models.HomeModel
import com.elsharif.bookhaven.databinding.ItemBodBinding
import com.elsharif.bookhaven.databinding.ItemBookBinding
import com.elsharif.bookhaven.utlis.loadOnline
import com.elsharif.bookhaven.utlis.loadOnline2

class HomeChildAdapter(val list :ArrayList<BooksModel>,val context: Context):
    RecyclerView.Adapter<HomeChildAdapter.ChildViewHolder>()
{
    class ChildViewHolder(val binding:ItemBookBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(model: BooksModel,context: Context){
            binding.apply {

                model.apply {
                    imagebookMain.loadOnline(image)
                   // imagebook.loadOnline2(image)


                    /*
                    Glide.with(itemView.context)
                        .load(image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .thumbnail(0.5f)
                        .into(imagebook)
*/

                    Toast.makeText(context,"image Url in Child is :$image", Toast.LENGTH_LONG).show()

                    cardViewBook.setOnClickListener {
                        Intent().apply {
                            putExtra("book_model",model)
                            setClass(context,DetailsActivity::class.java)
                            val options=ActivityOptions.makeSceneTransitionAnimation(
                                context as Activity,
                                cardViewBook,
                                cardViewBook.transitionName)
                            context.startActivity(this,options.toBundle())
                        }
                    }

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return  ChildViewHolder(ItemBookBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model, context)

    }

}