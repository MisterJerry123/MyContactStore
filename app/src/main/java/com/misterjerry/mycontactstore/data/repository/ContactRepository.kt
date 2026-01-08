package com.misterjerry.mycontactstore.data.repository

import com.misterjerry.mycontactstore.domain.model.Contact

interface ContactRepository {

    fun getAllContacts() : List<Contact>
}