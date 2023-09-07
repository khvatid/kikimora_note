package khvatid.kikimora.note.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khvatid.core.navigation.NavigationFlow
import khvatid.kikimora.note.presentation.ui.NoteScreen

fun NavGraphBuilder.noteScreen() = composable(route = NavigationFlow.Notes()) {
    NoteScreen()
}