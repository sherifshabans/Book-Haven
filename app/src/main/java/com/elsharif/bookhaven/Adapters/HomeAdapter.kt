package com.elsharif.bookhaven.Adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.elsharif.bookhaven.CategoryActivity
import com.elsharif.bookhaven.DetailsActivity
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.Models.HomeModel
import com.elsharif.bookhaven.databinding.ItemBodBinding
import com.elsharif.bookhaven.databinding.ItemHomeBinding
import com.elsharif.bookhaven.utlis.SpringScrollHelper
import com.elsharif.bookhaven.utlis.loadOnline


const val LAYOUT_HOME=0
const val LAYOUT_BOD = 1

class HomeAdapter(val list: ArrayList<HomeModel>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HomeItemViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        val mViewPool = RecyclerView.RecycledViewPool()
        fun bind(model: HomeModel, context: Context) {
            binding.apply {
                model.apply {
                    mCategoryTitle.text = catTitle

                    mSeeAllBtn.setOnClickListener {
                        // handle here
                        val intent = Intent()
                        intent.putExtra("book_list",booksList)
                        intent.setClass(context,CategoryActivity::class.java)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context as Activity,
                            mChildRvBooks,
                            mChildRvBooks.transitionName
                        )
                        context.startActivity(intent,options.toBundle())
                    }

                    Log.i("the BoolList","BookList Size is ${booksList?.size}")


                    if (booksList != null) {
                        mChildRvBooks.setupChildRv(booksList, context)
                    }
                }
            }

        }

        private fun RecyclerView.setupChildRv(list: ArrayList<BooksModel>, context: Context) {
            val adapter = HomeChildAdapter(list, context)
            this.adapter = adapter
            setRecycledViewPool(mViewPool)
            SpringScrollHelper().attachToRecyclerView(this)
        }
    }

    class BODItemViewHolder(val binding: ItemBodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeModel, context: Context) {

            binding.apply {
                model.bod?.apply {
                    imageView.loadOnline(image)
                    mReadBookBtn.setOnClickListener {
                        //
                        Intent().apply {
                            putExtra("book_model",model.bod)
                            setClass(context, DetailsActivity::class.java)
                            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                context as Activity,
                                cardView,
                                cardView.transitionName
                            )
                            context.startActivity(this,options.toBundle())
                        }
                    }
                }
            }


        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = list[position]
        return when (model.LAYOUT_TYPE) {
            LAYOUT_HOME -> LAYOUT_HOME
            else -> LAYOUT_BOD
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LAYOUT_BOD -> {
                BODItemViewHolder(
                    ItemBodBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )

            }

            else -> {
                HomeItemViewHolder(
                    ItemHomeBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        when (model.LAYOUT_TYPE) {
            LAYOUT_BOD -> {
                (holder as BODItemViewHolder).bind(model, context)
            }
            else -> {
                (holder as HomeItemViewHolder).bind(model, context)
            }
        }
    }
}