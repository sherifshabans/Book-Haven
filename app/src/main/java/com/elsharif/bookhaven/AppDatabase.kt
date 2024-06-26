package com.elsharif.bookhaven

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elsharif.bookhaven.daos.BooksmarksDao
import com.elsharif.bookhaven.daos.NotesDao
import com.elsharif.bookhaven.entities.BooksmarksEntity
import com.elsharif.bookhaven.entities.NotesEntity

@Database(entities = [BooksmarksEntity::class,NotesEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase() {

    abstract fun booksmarkDao():BooksmarksDao
    abstract fun notesDao():NotesDao

    companion object{
        private var INSTANCE:AppDatabase?=null

        fun getDatabse(context: Context):AppDatabase?{
            if(INSTANCE==null){
                INSTANCE= synchronized(this){
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "book_dp"
                    ).build()

                }
            }
            return INSTANCE
        }
    }
}