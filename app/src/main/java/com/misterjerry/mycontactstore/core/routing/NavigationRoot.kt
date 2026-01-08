package com.misterjerry.mycontactstore.core.routing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.misterjerry.mycontactstore.domain.model.Contact
import com.misterjerry.mycontactstore.presentation.home.HomeRoot
import com.misterjerry.mycontactstore.presentation.home.HomeScreen

@Composable
fun NavigationRoot() {

    val backstack = rememberNavBackStack(Route.Home)

    NavDisplay(
        backStack = backstack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Home> {
                HomeRoot()
            }
        }
    )



}
