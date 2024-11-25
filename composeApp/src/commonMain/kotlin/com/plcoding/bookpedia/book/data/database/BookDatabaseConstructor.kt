package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor


@Suppress("No_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<FavouriteBookDatabase> {
    override fun initialize(): FavouriteBookDatabase
}