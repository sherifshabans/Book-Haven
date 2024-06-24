package com.elsharif.bookhaven.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Repository.MainRepo

class MainViewModelFactory (private val repo : MainRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}