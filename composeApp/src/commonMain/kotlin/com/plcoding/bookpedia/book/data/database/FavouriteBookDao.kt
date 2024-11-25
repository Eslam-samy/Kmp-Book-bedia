package com.plcoding.bookpedia.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteBookDao {

    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getFavouriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :bookId")
    suspend fun getFavouriteBookById(bookId: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :bookId")
    suspend fun deleteFavouriteBookById(bookId: String)

}