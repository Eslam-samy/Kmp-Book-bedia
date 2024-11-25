package com.plcoding.bookpedia.book.data.mappers

import com.plcoding.bookpedia.book.data.database.BookEntity
import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.book.domain.Book


fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKry}-L.jpg"

        },
        authors = authorNamed ?: emptyList(),
        description = null,
        firstPublishedYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numberOfPages = numberOfPagesMedian,
        numEditions = editionCount ?: 0,
        languages = languages ?: emptyList()

    )
}

fun BookEntity.toBook(): Book = Book(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    languages = languages,
    authors = authors,
    firstPublishedYear = firstPublishYear,
    averageRating = ratingsAverage,
    ratingCount = ratingsCount,
    numberOfPages = numberPagesMedian,
    numEditions = numEditions,
)

fun Book.toBookEntity(): BookEntity = BookEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    languages = languages,
    authors = authors,
    numEditions = numEditions,
    firstPublishYear = firstPublishedYear,
    ratingsAverage = averageRating,
    ratingsCount = ratingCount,
    numberPagesMedian = numberOfPages,
)