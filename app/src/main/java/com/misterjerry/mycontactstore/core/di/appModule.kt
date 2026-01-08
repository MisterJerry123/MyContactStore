package com.misterjerry.mycontactstore.core.di

import androidx.room.Room
import com.misterjerry.mycontactstore.data.bookmarkContact.BookmarkContactDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module{
    single {
        Room.databaseBuilder(
            androidContext(),
            BookmarkContactDatabase::class.java,
            "bookmark_contact.db"
        )
            .build()
    }

    single { get<BookmarkContactDatabase>().bookmarkContactDao() }

}