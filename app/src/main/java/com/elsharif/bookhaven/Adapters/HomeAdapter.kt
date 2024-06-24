package com.elsharif.bookhaven.Adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.elsharif.bookhaven.CategoryActivity
import com.elsharif.bookhaven.DetailsActivity
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.Models.HomeModel
import com.elsharif.bookhaven.databinding.ItemBodBinding
import com.elsharif.bookhaven.databinding.ItemHomeBinding
import com.elsharif.bookhaven.utlis.loadOnline


const val LAYOUT_HOME=0
const val LAYOUT_BOD=1

class HomeAdapter(val list:ArrayList<HomeModel>,val context:Context):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        class HomeItemViewHolder(val binding: ItemHomeBinding):
        RecyclerView.ViewHolder(binding.root){
        val mViewPool =RecyclerView.RecycledViewPool()

        fun bind(model: HomeModel,context: Context){
            binding.apply {
             model.apply {

                mCategoryTitle.text = catTitle

                mSeeAllBtn.setOnClickListener {

                    val intent =Intent()
                    intent.putExtra("book_list",bookList)
                    intent.setClass(context,CategoryActivity::class.java)
                    val options= ActivityOptions.makeSceneTransitionAnimation(
                        context as Activity,
                        mChaildRvBooks,
                        mChaildRvBooks.transitionName)
                    context.startActivity(intent,options.toBundle())
                }
                 if (bookList != null) {
                     mChaildRvBooks.setupChildRv(bookList, context)
                 }

             }
            }

        }
        private fun RecyclerView.setupChildRv(list: ArrayList<BooksModel>,context:Context){
            val adapter= HomeChildAdapter(list, context )
            this.adapter= adapter
            setRecycledViewPool(mViewPool)
        }
    }
    class BODItemViewHolder(val binding: ItemBodBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(model: HomeModel,context: Context){

            binding.apply {
                model.bod?.apply {
                    imagebook1.loadOnline(image)
                    Toast.makeText(context,"image Url is :$image",Toast.LENGTH_LONG).show()

                    mReadBookBtn.setOnClickListener {
                        //
                        Intent().apply {
                            putExtra("book_model",model.bod)
                            setClass(context, DetailsActivity::class.java)
                            val options= ActivityOptions.makeSceneTransitionAnimation(
                                context as Activity,
                                cardView1,
                                cardView1.transitionName)
                            context.startActivity(this,options.toBundle())

                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model =list[position]
        return when(model.LAYOUR_TYPE){
            LAYOUT_HOME-> LAYOUT_HOME
            else -> LAYOUT_BOD
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(viewType ){
            LAYOUT_HOME->{
                HomeItemViewHolder(
                    ItemHomeBinding.inflate(
                        LayoutInflater.from(context),parent,false))
            }
           else ->{
               BODItemViewHolder(ItemBodBinding.inflate(LayoutInflater.from(context),parent,false))
           }
        }

    }

    override fun getItemCount(): Int=list.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        when(model.LAYOUR_TYPE){
            LAYOUT_HOME-> {
                (holder as HomeItemViewHolder).bind(model,context)
            }
            else -> {
                (holder as BODItemViewHolder).bind(model,context)

            }
        }

    }
}