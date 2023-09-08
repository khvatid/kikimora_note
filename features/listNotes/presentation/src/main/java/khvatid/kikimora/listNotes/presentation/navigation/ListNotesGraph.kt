package khvatid.kikimora.listNotes.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khvatid.core.navigation.NavigationFlow
import khvatid.kikimora.listNotes.presentation.ui.screen.ListScreen

fun NavGraphBuilder.listNotesGraph(navigateToNote: (Int) -> Unit) =
    composable(route = NavigationFlow.ListNotes()) {
        ListScreen(navigateToNote = navigateToNote)
    }
