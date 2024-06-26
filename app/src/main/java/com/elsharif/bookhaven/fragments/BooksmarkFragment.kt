package com.elsharif.bookhaven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Adapters.BookmarksAdapter
import com.elsharif.bookhaven.Adapters.ToolsAdapter
import com.elsharif.bookhaven.AppDatabase
import com.elsharif.bookhaven.Models.ToolsModel
import com.elsharif.bookhaven.PdfActivity
import com.elsharif.bookhaven.R
import com.elsharif.bookhaven.databinding.FragmentBooksmarkBinding
import com.elsharif.bookhaven.viewmodel.ToolsViewModel
import com.elsharif.bookhaven.viewmodel.ToolsViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksmarkFragment : BottomSheetDialogFragment() {
    private val binding by lazy {
          FragmentBooksmarkBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        val mActivity= requireActivity() as PdfActivity
        ViewModelProvider(mActivity, ToolsViewModelFactory(mActivity))[ToolsViewModel::class.java]
    }

    private val list:MutableList<Int> = mutableListOf()
    private val adapter by lazy {
        BookmarksAdapter(list=list, viewModel=viewModel,context=requireActivity(), fragment = this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            mBookmarksRv.adapter=adapter
            getData()
        }

    }

    private fun getData() {
        val mActivity = requireActivity() as PdfActivity
        val database = AppDatabase.getDatabse(requireActivity())
        CoroutineScope(Dispatchers.IO).launch {
            database?.booksmarkDao()?.getBooksmarks(mActivity.bookId)?.forEach {
                list.add(it.pageNo)
            }
            mActivity.runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )=binding.root



}