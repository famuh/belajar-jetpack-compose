package com.example.booksapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.booksapp.model.Book
import com.example.booksapp.model.BooksData
import com.example.booksapp.ui.theme.BooksAppTheme

@Composable
fun DetailScreen(
    itemIndex: Int,
    navigateBack: () -> Unit,
) {
    DetailContent(1)

}

@Composable
fun DetailContent(
    itemIndex: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(8.dp)
    ) {

        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = BooksData.books[itemIndex],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .height(100.dp)
                        .clip(
                            CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Judul Buku",
                        color = Color.DarkGray,
                        fontSize = 10.sp
                    )
                    Text(
                        text = BooksData.books[itemIndex].name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
//                        style = MaterialTheme.typography.h5.copy(
//                            fontWeight = FontWeight.ExtraBold
//                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Penulis",
                        color = Color.DarkGray,
                        fontSize = 10.sp
                    )
                    Text(
                        text = BooksData.books[itemIndex].author,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
//                        style = MaterialTheme.typography.h5.copy(
//                            fontWeight = FontWeight.ExtraBold
//                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tahun Terbit", color = Color.DarkGray,
                        fontSize = 10.sp
                    )
                    Text(
                        text = BooksData.books[itemIndex].published, fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Deskripsi",
            color = Color.DarkGray,
            fontSize = 10.sp
        )
        Text(
            text = BooksData.books[itemIndex].description,
            fontSize = 10.sp
        )
    }


}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailScreenPreview() {
    DetailScreen(itemIndex = 1) {

    }
}

@Preview
@Composable
fun DetailContentPreview() {
    BooksAppTheme {
        DetailContent(1)
    }
}