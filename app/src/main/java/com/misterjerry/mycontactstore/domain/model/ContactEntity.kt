package com.misterjerry.mycontactstore.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactEntity(
    val name: String,
    @PrimaryKey val phoneNumber: String,
)
