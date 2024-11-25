package com.plcoding.bookpedia.book.data.network

import cmp_bookpedia.composeapp.generated.resources.Res
import com.plcoding.bookpedia.book.data.dto.BookWorkDto
import com.plcoding.bookpedia.book.data.dto.SearchResponseDto
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.data.safeCall
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteDataSource(
    private val client: HttpClient,
) : RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?,
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall {
            client.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                resultLimit?.let { parameter("limit", it) }
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,first_publish_year,author_name,cover_i,language,cover_edition_key,edition_count,number_of_pages_median,ratings_average,ratings_count"
                )

            }
        }

    }

    override suspend fun getBookDetail(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {
        return safeCall<BookWorkDto> {
            client.get(
                urlString = "$BASE_URL/works/$bookWorkId.json"
            )
        }
    }
}