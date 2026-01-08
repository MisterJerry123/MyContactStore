package com.misterjerry.mycontactstore.core.di

import com.misterjerry.mycontactstore.data.repository.ContactRepository
import com.misterjerry.mycontactstore.domain.repository.ContactRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<ContactRepository> { ContactRepositoryImpl(androidContext(), get()) }
}