package com.plcoding.bookpedia.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context,
) {
    actual fun createDatabase(): RoomDatabase.Builder<FavouriteBookDatabase> {
        val applicationContext = context.applicationContext
        val dbFile = applicationContext.getDatabasePath(FavouriteBookDatabase.DATABASE_NAME)
        return Room.databaseBuilder(
            applicationContext,
            dbFile.absolutePath
        )
    }
}