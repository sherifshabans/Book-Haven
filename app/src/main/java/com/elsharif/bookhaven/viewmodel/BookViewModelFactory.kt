package com.elsharif.bookhaven.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Repository.BookRepo

class BookViewModelFactory (private val repo :BookRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(repo) as T
    }
}