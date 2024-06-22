package com.elsharif.bookhaven

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.elsharif.bookhaven.databinding.ActivityHomeBinding

class BooksAdapter( val list :ArrayList<BooksModel>,val context: Context):
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    class ViewHolder (val binding:  ActivityHomeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ActivityHomeBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int = list.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model =list[position]
        holder.binding.apply {
            imagebook.setBackgroundResource(model.image)
            cardView.setOnClickListener {
                val intent = Intent()
                intent.putExtra("book_title",model.title)
                intent.putExtra("book_description",model.description)
                intent.putExtra("book_pdf",model.bookPDF)
                intent.putExtra("book_image",model.image)
                intent.setClass(context,DetailsActivity::class.java)
                val options=ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity,imagebook,"BookTrans")
                context.startActivity(intent,options.toBundle())
            }
        }
    }



}