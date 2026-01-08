package com.misterjerry.mycontactstore.data.repository

import com.misterjerry.mycontactstore.domain.model.Contact

interface ContactRepository {
    suspend fun getAllContacts(): List<Contact>
    suspend fun addBookmark(contact: Contact)
    suspend fun removeBookmark(contact: Contact)
    suspend fun getAllBookmarks(): List<Contact>
}