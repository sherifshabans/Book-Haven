package com.elsharif.bookhaven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Adapters.ToolsAdapter
import com.elsharif.bookhaven.Models.ToolsModel
import com.elsharif.bookhaven.PdfActivity
import com.elsharif.bookhaven.R
import com.elsharif.bookhaven.databinding.FragmentPdfToolsBinding
import com.elsharif.bookhaven.enums.ToolsType
import com.elsharif.bookhaven.viewmodel.ToolsViewModel
import com.elsharif.bookhaven.viewmodel.ToolsViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PdfToolsFragment : BottomSheetDialogFragment() {

    private val binding by lazy {
        FragmentPdfToolsBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        val mActivity= requireActivity() as PdfActivity
        ViewModelProvider(mActivity, ToolsViewModelFactory(mActivity))[ToolsViewModel::class.java]
    }

    private val list:MutableList<ToolsModel> = mutableListOf()
    private val adapter by lazy {
        ToolsAdapter(list=list, viewModel=viewModel,context=requireActivity(), fragment = this )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvinit()
    }

    private fun rvinit() {
        binding.mToolsRv.adapter=adapter

        list.apply {
            add(ToolsModel("Bookmark Me",R.drawable.ic_bookmarks,ToolsType.ADD_TO_BOOKMARKS))
            add(ToolsModel("My Bookmarks",R.drawable.ic_all_bookmarks,ToolsType.BOOKMARKS))
            add(ToolsModel("My Notes",R.drawable.ic_note,ToolsType.NOTES))
            add(ToolsModel("Night Mode",R.drawable.ic_night_mode,ToolsType.NIGHT_MODE))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )=binding.root


}