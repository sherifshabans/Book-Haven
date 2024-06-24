package com.elsharif.bookhaven

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.elsharif.bookhaven.Adapters.CategoryAdapter
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    private val activity =this

    private val list =ArrayList<BooksModel>()
    private val adapter =CategoryAdapter(list,activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            mRvCategory.adapter =adapter
            val bookList= intent.getSerializableExtra("book_list") as ArrayList<BooksModel>
            Toast.makeText(this@CategoryActivity,"bookList ${bookList.size}",Toast.LENGTH_LONG).show()
            bookList.forEach {
                list.add(it)
            }
        }


    }

    override fun onBackPressed() {
        finish()
        with(window){
            sharedElementReenterTransition =null
            sharedElementReturnTransition =null
        }
        binding.mRvCategory.transitionName=null
    }
}