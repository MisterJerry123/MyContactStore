package com.misterjerry.mycontactstore.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misterjerry.mycontactstore.data.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.misterjerry.mycontactstore.core.util.KoreanChoseongSearcher

import com.misterjerry.mycontactstore.domain.model.Contact


class HomeViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private var allContacts: List<Contact> = emptyList()
    private var currentQuery: String = ""

    fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val initData = repository.getAllContacts()
            allContacts = initData
            filterContacts()
        }
    }

    fun onSearchQueryChanged(query: String) {
        currentQuery = query
        filterContacts()
    }

    fun onToggleFavorite(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            val newIsFavorite = !contact.isFavorite
            if (newIsFavorite) {
                repository.addBookmark(contact)
            } else {
                repository.removeBookmark(contact)
            }
            
            // Update local state
            allContacts = allContacts.map {
                if (it.name == contact.name && it.phoneNumber == contact.phoneNumber) {
                    it.copy(isFavorite = newIsFavorite)
                } else {
                    it
                }
            }
            filterContacts()
        }
    }

    private fun filterContacts() {
        val filtered = if (currentQuery.isBlank()) {
            allContacts
        } else {
            allContacts.filter { contact ->
                KoreanChoseongSearcher.match(contact.name, currentQuery) ||
                        contact.phoneNumber.contains(currentQuery)
            }
        }
        _state.value = _state.value.copy(
            contactList = filtered,
            searchQuery = currentQuery
        )
    }
}