package com.elsharif.bookhaven.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elsharif.bookhaven.entities.BooksmarksEntity

@Dao
interface BooksmarksDao{

    @Query("SELECT * from bookmarks WHERE bookId == :bookId")
    suspend fun getBooksmarks(bookId:String):List<BooksmarksEntity>

    @Insert
    suspend fun addBookmark(entity: BooksmarksEntity)

}