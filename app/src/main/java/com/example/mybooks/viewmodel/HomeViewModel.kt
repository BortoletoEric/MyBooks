package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookRepository.getInstance(application.applicationContext)
    val booksList: LiveData<List<BookEntity>> = repository.getAllBooks().asLiveData()

    fun favorite(bookId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            // This moves the work off the Main Thread
            repository.toggleFavoriteStatus(bookId)
        }
    }
}