package com.misterjerry.mycontactstore.domain.model

data class Contact(
    val name: String,
    val phoneNumber: String,
    val isFavorite: Boolean = false
)
