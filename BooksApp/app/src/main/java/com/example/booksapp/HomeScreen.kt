package com.example.booksapp

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.booksapp.model.Book
import com.example.booksapp.model.BooksData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    HomeContent(
//        modifier = modifier,
        navigateToDetail = navigateToDetail,
    )
}

@Composable
fun HomeContent(
//    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    LazyColumn {
        itemsIndexed(BooksData.books) { index, book ->
            BookItem(
                imageUrl = book.imageUrl,
                name = book.name,
                modifier = Modifier.clickable {
                    navigateToDetail(index)
                    Log.d("Home Screen", "Mengirim $index ke detail")
                })
        }
    }
}


@Composable
fun BookItem(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier,

    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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