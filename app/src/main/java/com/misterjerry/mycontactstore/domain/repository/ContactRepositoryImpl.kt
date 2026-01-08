package com.misterjerry.mycontactstore.domain.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.misterjerry.mycontactstore.data.bookmarkContact.BookmarkContactDao
import com.misterjerry.mycontactstore.data.repository.ContactRepository
import com.misterjerry.mycontactstore.domain.model.Contact
import com.misterjerry.mycontactstore.domain.model.ContactEntity

class ContactRepositoryImpl(
    private val context: Context,
    private val bookmarkDao: BookmarkContactDao
) : ContactRepository {
    override suspend fun getAllContacts(): List<Contact> {
        return try {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) 
                != PackageManager.PERMISSION_GRANTED) {
                return emptyList()
            }

            // Fetch bookmarks
            val bookmarks = bookmarkDao.getAllBookmarkContact().map { it.phoneNumber }.toSet()

            val contacts = mutableListOf<Contact>()
            val cursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.use {
                val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                while (it.moveToNext()) {
                    val name = if (nameIndex >= 0) it.getString(nameIndex) else "Unknown"
                    val number = if (numberIndex >= 0) it.getString(numberIndex) else ""
                    contacts.add(Contact(name, number, isFavorite = bookmarks.contains(number)))
                }
            }
            contacts
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun addBookmark(contact: Contact) {
        bookmarkDao.addBookmarkContact(ContactEntity(name = contact.name, phoneNumber = contact.phoneNumber))
    }

    override suspend fun removeBookmark(contact: Contact) {
        bookmarkDao.deleteBookmarkContact(ContactEntity(name = contact.name, phoneNumber = contact.phoneNumber))
    }

    override suspend fun getAllBookmarks(): List<Contact> {
        return bookmarkDao.getAllBookmarkContact().map { 
            Contact(it.name, it.phoneNumber, isFavorite = true) 
        }
    }
}