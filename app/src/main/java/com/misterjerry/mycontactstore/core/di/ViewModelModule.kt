package com.misterjerry.mycontactstore.core.di

import com.misterjerry.mycontactstore.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { HomeViewModel(get()) }
}