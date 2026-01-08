package com.misterjerry.mycontactstore.data.bookmarkContact

import androidx.room.Database
import androidx.room.RoomDatabase
import com.misterjerry.mycontactstore.domain.model.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class BookmarkContactDatabase : RoomDatabase(){
    abstract fun bookmarkContactDao() : BookmarkContactDao
}