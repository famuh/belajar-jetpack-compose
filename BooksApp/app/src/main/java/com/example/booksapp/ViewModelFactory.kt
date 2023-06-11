package com.example.booksapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksapp.data.BookRepository

class ViewModelFactory(private val repository: BookRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}