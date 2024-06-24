package com.elsharif.bookhaven.Models

import com.elsharif.bookhaven.Adapters.LAYOUT_HOME

data class HomeModel(

    val catTitle :String?=null,
    val bookList:ArrayList<BooksModel>?=null,
    val bod:BooksModel?=null,
    val LAYOUR_TYPE:Int=LAYOUT_HOME
)
