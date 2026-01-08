package com.misterjerry.mycontactstore.domain.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.misterjerry.mycontactstore.data.repository.ContactRepository
import com.misterjerry.mycontactstore.domain.model.Contact

class ContactRepositoryImpl(private val context: Context) : ContactRepository {
    override fun getAllContacts(): List<Contact> {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) 
            != PackageManager.PERMISSION_GRANTED) {
            return emptyList()
        }

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
                contacts.add(Contact(name, number))
            }
        }
        return contacts
    }
}