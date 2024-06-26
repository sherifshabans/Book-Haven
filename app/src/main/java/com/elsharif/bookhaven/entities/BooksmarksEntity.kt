package com.elsharif.bookhaven.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BooksmarksEntity (
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val bookId:String,
    val pageNo:Int
)