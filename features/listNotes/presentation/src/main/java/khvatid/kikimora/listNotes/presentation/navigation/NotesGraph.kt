package khvatid.kikimora.listNotes.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khvatid.core.navigation.NavigationFlow
import khvatid.kikimora.listNotes.presentation.ui.screens.list.ListScreen

fun NavGraphBuilder.notesGraph() = navigation(startDestination = "list", route = NavigationFlow.Notes()){
    composable(route = "list"){
        ListScreen()
    }
    composable(route = "note"){

    }

}