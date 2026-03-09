package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.entity.BookEntity
import com.example.mybooks.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    private val repository = BookRepository.getInstance(application.applicationContext)

    fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _books.postValue(repository.getAllBooks())
        }
    }

    fun favorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavoriteStatus(id)
        }
    }
}