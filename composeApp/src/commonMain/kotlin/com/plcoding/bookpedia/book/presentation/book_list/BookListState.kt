package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val searchResults: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val searchQuery: String = "kotlin",
    val favourites: List<Book> = emptyList(),
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,
)


