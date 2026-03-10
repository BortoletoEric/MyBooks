package com.example.mybooks.repository

import android.content.Context
import com.example.mybooks.entity.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository private constructor(context: Context) {

    private var database = BookDataBase.getDataBase(context).bookDAO()
    companion object {
        private lateinit var instance: BookRepository

        fun getInstance(context: Context): BookRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }
    }

    fun getAllBooks(): Flow<List<BookEntity>> {
        return database.getAllBooks()
    }

    fun getFavoriteBooks(): Flow<List<BookEntity>> {
        return database.getFavoriteBooks()
    }

    suspend fun getBookById(id: Int): BookEntity {
        return database.getBookById(id)
    }

    suspend fun toggleFavoriteStatus(id: Int) {
        val book = getBookById(id)
        book.favorite = !book.favorite
        database.update(book)
    }

    suspend fun deleteBook(id: Int): Boolean {
        return database.delete(getBookById(id)) > 0
    }
}
