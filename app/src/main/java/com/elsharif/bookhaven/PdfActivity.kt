package com.elsharif.bookhaven

import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.databinding.ActivityPdfBinding
import com.elsharif.bookhaven.fragments.PdfToolsFragment
import com.elsharif.bookhaven.utlis.loadBannerAd
import com.elsharif.bookhaven.viewmodel.ToolsViewModel
import com.elsharif.bookhaven.viewmodel.ToolsViewModelFactory
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle


class PdfActivity : AppCompatActivity() {
    val activity = this
    lateinit var binding: ActivityPdfBinding


    lateinit var bookPdf: String
    lateinit var bookId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupBasicViews()
        binding.apply {
            bookPdf = intent.getStringExtra("book_pdf").toString()
            bookId = intent.getStringExtra("book_id").toString()
            mBannerAd.loadBannerAd()

            pdfView.fromUri(bookPdf.toUri())
                .swipeHorizontal(false)
                .scrollHandle(DefaultScrollHandle(activity))
                .enableSwipe(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .load()
        }

    }

    private fun setupBasicViews() {
        binding.mToolsFab.setOnClickListener {
            val toolsBottomSheet = PdfToolsFragment()
            toolsBottomSheet.show(supportFragmentManager, toolsBottomSheet.tag)
        }

    }
}
