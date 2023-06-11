package com.example.booksapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.booksapp.data.BookRepository
import com.example.booksapp.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _books = MutableStateFlow(
        repository.getBooks()
    )

    val books: MutableStateFlow<List<Book>> get() = _books

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

//    fun search(newQuery: String) {
//        _query.value = newQuery
//        _groupedHeroes.value = repository.searchHeroes(_query.value)
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }
//    }
}