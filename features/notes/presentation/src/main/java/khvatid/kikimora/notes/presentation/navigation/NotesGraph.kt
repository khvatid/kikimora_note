package khvatid.kikimora.notes.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import khvatid.core.navigation.NavigationFlow

fun NavGraphBuilder.notesGraph() = navigation(startDestination = "", route = NavigationFlow.Notes()){
    composable(route = ""){

    }
    composable(route = ""){

    }
}