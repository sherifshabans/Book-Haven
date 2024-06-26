package com.elsharif.bookhaven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.elsharif.bookhaven.Adapters.BookmarksAdapter
import com.elsharif.bookhaven.Adapters.NotesAdpater
import com.elsharif.bookhaven.AppDatabase
import com.elsharif.bookhaven.PdfActivity
import com.elsharif.bookhaven.R
import com.elsharif.bookhaven.databinding.FragmentBooksmarkBinding
import com.elsharif.bookhaven.databinding.FragmentNotesBinding
import com.elsharif.bookhaven.entities.NotesEntity
import com.elsharif.bookhaven.viewmodel.ToolsViewModel
import com.elsharif.bookhaven.viewmodel.ToolsViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesFragment : BottomSheetDialogFragment() {
    private val binding by lazy {
        FragmentNotesBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        val mActivity= requireActivity() as PdfActivity
        ViewModelProvider(mActivity, ToolsViewModelFactory(mActivity))[ToolsViewModel::class.java]
    }

    private val notesList:MutableList<NotesEntity> = mutableListOf()
    private val adapter by lazy {
        NotesAdpater(list=notesList,context=requireActivity())

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            mNotesRv.adapter=adapter
             getData()
             addNote()

        }

    }

    private fun addNote() {
        binding.apply {
            btnAddNote.setOnClickListener {
                val note = edtNote.text.toString()
                if (note.isEmpty()) {
                    Toast.makeText(
                        requireActivity(),
                        "Please add note to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.addNote(note)
                edtNote.text = null
                getData()


            }
        }
    }

    private fun getData() {
        val pdfActivity = requireActivity() as PdfActivity
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getDatabse(requireActivity())
            notesList.clear()
            database?.notesDao()?.getNotes(pdfActivity.bookId)?.forEach {
                notesList.add(it)
            }
            pdfActivity.runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )=binding.root


}