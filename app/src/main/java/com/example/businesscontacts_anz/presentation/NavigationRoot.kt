package com.example.businesscontacts_anz.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.businesscontacts_anz.presentation.screens.ContactDetailsScreenRoot
import com.example.businesscontacts_anz.presentation.screens.ContactListScreenRoot
import com.example.businesscontacts_anz.presentation.viewmodel.BusinessContactViewModel


@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues
){
    NavHost(
        navHostController,
        route = "root",
        startDestination = "ListScreen"
    ){
        composable("ListScreen") {
            ContactListScreenRoot(
                paddingValues = paddingValues,
                viewModel = it.getNavGraphScopedSharedViewModel("root", navHostController),
                onContactClicked = {
                    navHostController.navigate("DetailScreen")
                }
            )
        }

        composable("DetailScreen") {
            ContactDetailsScreenRoot(
                paddingValues = paddingValues,
                viewModel = it.getNavGraphScopedSharedViewModel("root", navHostController),
                onBackButtonClicked = {
                    navHostController.popBackStack(
                        route = "ListScreen",
                        inclusive = false
                    )
                }
            )
        }

    }
}

@Composable
private fun NavBackStackEntry.getNavGraphScopedSharedViewModel(navGraphRoute:String, navHostController: NavHostController): BusinessContactViewModel {
    val parentEntry = remember(this) {
        navHostController.getBackStackEntry(navGraphRoute)
    }
    val sharedViewModel = hiltViewModel<BusinessContactViewModel>(parentEntry)
    return sharedViewModel
}
