package com.elsharif.bookhaven.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Repository.BookRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(val repo:BookRepo): ViewModel() {

    val downloadLiveData get() =repo.downloadLiveData

    fun downloadFile(url:String,fileName:String){
        CoroutineScope(Dispatchers.IO).launch {
            repo.downLoadPdf(
                url = url,
                firstName = fileName
            )
        }
    }
}