package com.example.mybooks.repository

import android.content.Context
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.helper.DatabaseConstants

class BookRepository private constructor(context: Context) {

    private var database = BookDatabaseHelper(context)

    private val books = mutableListOf<BookEntity>()

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

    fun getAllBooks(): List<BookEntity> {
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        val cursor = db.query(DatabaseConstants.BOOK.TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.TITLE))
                val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                books.add(BookEntity(id, title, author, favorite, genre))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return books
    }

    fun getFavoriteBooks(): List<BookEntity> {
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        val cursor = db.query(DatabaseConstants.BOOK.TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.TITLE))
                val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                if (favorite) books.add(BookEntity(id, title, author, favorite, genre))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return books
    }

    fun getBookById(id: Int): BookEntity? {
        return books.find { it.id == id }
    }

    fun deleteBook(id: Int): Boolean {
        return books.removeIf { it.id == id }
        // tratar atualizações de livros
    }

    fun toggleFavoriteStatus(id: Int) {
        val book = books.find { it.id == id }
        if (book != null) {
            book.favorite = !book.favorite
        }
    }
}
