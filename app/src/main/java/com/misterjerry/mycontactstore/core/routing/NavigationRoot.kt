package com.misterjerry.mycontactstore.core.routing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.misterjerry.mycontactstore.domain.model.Contact
import com.misterjerry.mycontactstore.presentation.home.HomeScreen

@Composable
fun NavigationRoot() {

    val backstack = rememberNavBackStack(Route.Home)

    NavDisplay(
        backStack = backstack,
        entryDecorators = listOf(
            rememberViewModelStoreNavEntryDecorator(),
            rememberSaveableStateHolderNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Home> {
                HomeScreen(
                    contacts = mockContacts,
                )
            }
        }
    )



}
val mockContacts = listOf(
    Contact("홍길동", "010-1234-5678"),
    Contact("김철수", "010-2222-3333"),
    Contact("이영희", "010-4444-5555"),
    Contact("박민수", "010-6666-7777"),
    Contact("최지우", "010-8888-9999"),
)