package com.elsharif.bookhaven

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Models.BooksModel
import com.elsharif.bookhaven.Repository.BookRepo
import com.elsharif.bookhaven.databinding.ActivityDetailsBinding
import com.elsharif.bookhaven.databinding.LayoutProgressBinding
import com.elsharif.bookhaven.utlis.MyResponsers
import com.elsharif.bookhaven.utlis.loadOnline
import com.elsharif.bookhaven.viewmodel.BookViewModel
import com.elsharif.bookhaven.viewmodel.BookViewModelFactory

class DetailsActivity : AppCompatActivity() {
    val activity =this
    lateinit var binding: ActivityDetailsBinding

    private val repo =BookRepo(activity)
    private val viewModel by lazy {
        ViewModelProvider(
            activity,
            BookViewModelFactory(repo)
        )[BookViewModel::class.java]

    }

    private val TAG="Details_Activity"
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bookModel =intent.getSerializableExtra("book_model") as BooksModel

        binding.apply {

            bookModel.apply {
                mBooktitle.text =title
                mAuthourName.text=author
                mBookDes.text=description
                mBookImage.loadOnline(image)
            }

            mReadBook.setOnClickListener {
                viewModel.downloadFile(bookModel.bookPDF,"${bookModel.title}.pdf")
            }
            val dialogBinding =LayoutProgressBinding.inflate(layoutInflater)
            val dialog= Dialog(activity).apply {
                setCancelable(false)
                setContentView(dialogBinding.root)

                this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                this.window!!.setLayout(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )


            }
            viewModel.downloadLiveData.observe(activity){
                when(it){
                    is MyResponsers.Error -> {
                         dialog.dismiss()
                        Log.e("onCreate: ${it.errorMessage}","")
                    }
                    is MyResponsers.Loading -> {
                        dialogBinding.mProgress.text="${it.progress}%"
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        dialogBinding.mProgressBar.setProgress(it.progress,true)
                        }else {
                        dialogBinding.mProgressBar.progress=it.progress

                        }
                        dialog.show()
                        Log.i(TAG,"onCreate: Progress ${it.progress}")
                    }
                    is MyResponsers.Success -> {
                        dialog.dismiss()
                        Log.i(TAG,"onCreate: Downloaded ${it.data}")
                        Intent().apply {
                            putExtra("book_pdf",it.data?.filePath)
                            setClass(activity,PdfActivity::class.java)
                            startActivity(this)
                        }
                    }
                }
            }
        }

    }
}