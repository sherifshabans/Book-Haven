package com.elsharif.bookhaven

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.elsharif.bookhaven.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    val activity =this
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bookTitle =intent.getStringExtra("book_title").toString()
        val bookDesc =intent.getStringExtra("book_description").toString()
        val bookPdf =intent.getStringExtra("book_pdf").toString()
        val bookImage =intent.getIntExtra("book_image",0)

        binding.apply {
            mBooktitle.text=bookTitle
            mBookDes.text=bookDesc
            mBookImage.setBackgroundResource(bookImage)
            mReadBook.setOnClickListener {
                val intent =Intent()
                intent.putExtra("book_pdf",bookPdf)
                intent.setClass(activity,PdfActivity::class.java)
                startActivity(intent)
            }
        }

    }
}