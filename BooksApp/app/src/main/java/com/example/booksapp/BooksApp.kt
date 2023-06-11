package com.example.booksapp

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.booksapp.data.BookRepository
import com.example.booksapp.model.BooksData
import com.example.booksapp.model.BooksData.books

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
    viewModel: BookViewModel = viewModel(
        factory = ViewModelFactory(BookRepository())
    ),
) {
    val books by viewModel.books.collectAsState()

    Box(modifier = modifier) {
        val listState = rememberLazyListState()

        LazyColumn {
//            items(BooksData.books, key = { it.id }) { book ->
//                BookListItem(
//                    name = book.name,
//                    imageUrl = book.imageUrl,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }

            itemsIndexed(BooksData.books) {index, book ->
                BookListItem(
                    name = book.name,
                    imageUrl = book.imageUrl,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BookListItem(
    name: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(
                    CircleShape
                )
        )

        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )

    }

}