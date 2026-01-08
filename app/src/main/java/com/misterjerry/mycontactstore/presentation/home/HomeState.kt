package com.misterjerry.mycontactstore.presentation.home

import com.misterjerry.mycontactstore.domain.model.Contact

data class HomeState(
    val contactList: List<Contact> = emptyList(),
    val searchQuery: String = ""
)