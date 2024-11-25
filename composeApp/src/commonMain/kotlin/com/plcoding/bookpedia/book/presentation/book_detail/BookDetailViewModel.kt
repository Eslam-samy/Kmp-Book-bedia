package com.plcoding.bookpedia.book.presentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.app.Routes
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val bookId = savedStateHandle.toRoute<Routes.BookDetails>().id

    private val _state = MutableStateFlow(BookDetailsState())
    val state = _state
        .onStart {
            fetchBookDescription()
            observeFavStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun onAction(action: BookDetailAction) {

        when (action) {
            is BookDetailAction.OnSelectedBookChange -> {
                _state.value = _state.value.copy(
                    book = action.book
                )
            }

            BookDetailAction.OnFavouriteClick -> {
                viewModelScope.launch {
                    if (_state.value.isFavourite) {
                        repository.removeFavouriteBook(bookId)
                    } else {
                        _state.value.book?.let {
                            repository.addFavouriteBook(_state.value.book!!)
                        }
                    }
                }
            }

            else -> Unit
        }
    }

    private fun observeFavStatus() {
        repository
            .isBookFavourite(bookId)
            .onEach {
                _state.value = _state.value.copy(
                    isFavourite = it
                )
            }
            .launchIn(viewModelScope)
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            repository.getBookDescription(
                bookId
            ).onSuccess { description ->
                _state.value = _state.value.copy(
                    book = _state.value.book?.copy(
                        description = description
                    ),
                    isLoading = false
                )
            }

        }
    }

}