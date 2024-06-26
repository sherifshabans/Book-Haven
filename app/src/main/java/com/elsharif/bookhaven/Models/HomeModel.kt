package com.elsharif.bookhaven.Models

import com.elsharif.bookhaven.Adapters.LAYOUT_HOME

data class HomeModel(
    val catTitle :String?=null,
    val booksList:ArrayList<BooksModel>?=null,
    val bod:BooksModel?=null,
    val LAYOUT_TYPE:Int=LAYOUT_HOME
)
