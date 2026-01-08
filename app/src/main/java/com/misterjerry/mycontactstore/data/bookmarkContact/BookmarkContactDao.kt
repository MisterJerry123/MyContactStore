package com.misterjerry.mycontactstore.data.bookmarkContact

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.misterjerry.mycontactstore.domain.model.ContactEntity

@Dao
interface BookmarkContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmarkContact(contact: ContactEntity)

    @Delete
    suspend fun deleteBookmarkContact(contact: ContactEntity)

    @Query("SELECT * FROM contactentity")
    suspend fun getAllBookmarkContact(): List<ContactEntity>
}