package com.elsharif.bookhaven

import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.elsharif.bookhaven.databinding.ActivityPdfBinding

class PdfActivity : AppCompatActivity() {
    val activity =this
    lateinit var binding: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPdfBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.apply {
            val bookPdf=intent.getStringExtra("book_pdf").toString()
            pdfView.fromUri(Uri.parse(bookPdf))
                .swipeHorizontal(true)
                .enableSwipe(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .load()

        }

    }
}