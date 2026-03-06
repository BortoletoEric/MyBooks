package com.example.mybooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybooks.entity.BookEntity


@Database(entities = [BookEntity::class], version = 1)
abstract class BookDataBase : RoomDatabase() {

    abstract fun bookDAO(): BookDAO

    companion object {
        private lateinit var instance: BookDataBase
        private const val DATABASE_NAME = "book_db"

        fun getDataBase(context: Context): BookDataBase {
            if (!::instance.isInitialized) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, BookDataBase::class.java, DATABASE_NAME)
                            .addMigrations()
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance
        }
    }

}