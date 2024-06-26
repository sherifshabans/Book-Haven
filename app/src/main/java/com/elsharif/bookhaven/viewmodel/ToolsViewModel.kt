package com.elsharif.bookhaven.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elsharif.bookhaven.AppDatabase
import com.elsharif.bookhaven.PdfActivity
import com.elsharif.bookhaven.entities.BooksmarksEntity
import com.elsharif.bookhaven.entities.NotesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("ALL")
class ToolsViewModel(val pdfActivity: PdfActivity) : ViewModel() {

    private var nightMode = false
    val database = AppDatabase.getDatabse(pdfActivity)

    fun toggleNightMode() {
        nightMode = !nightMode
        pdfActivity.binding.pdfView.setNightMode(nightMode)

    }

    fun jumpToPage(pageNo: Int) {
        pdfActivity.binding.pdfView.jumpTo(pageNo, true)
    }

    fun addBookmark() {
        CoroutineScope(Dispatchers.IO).launch {
            val entity =
                BooksmarksEntity(0, pdfActivity.bookId, pdfActivity.binding.pdfView.currentPage)
            database?.booksmarkDao()?.addBookmark(entity)
        }
    }

    fun addNote(note: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val entity = NotesEntity(0, pdfActivity.bookId, note)
            database?.notesDao()?.addNote(entity)

        }
    }


}
