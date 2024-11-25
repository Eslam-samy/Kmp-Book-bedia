package com.plcoding.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview(showBackground = true)
@Composable
fun BookSearchBarPreview() {
    BookSearchBar(
        searchQuery = "Kotlin",
        onSearchQueryChange = {},
        onImeAction = {}
    )

}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        authors = listOf("Author $it"),
        averageRating = 4.678758,
        imageUrl = "https://picsum.photos/200/300?random=$it",
        description = "Description $it",
        languages = emptyList(),
        firstPublishedYear = null,
        numberOfPages = null,
        numEditions = 1,
        ratingCount = null,
    )
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {

    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {}
    )
}