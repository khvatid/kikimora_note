package khvatid.kikimora.note.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import khvatid.core.navigation.NavigationFlow
import khvatid.kikimora.note.presentation.ui.NoteScreen


fun NavController.navigateToNoteScreen(id : Int){
    this.navigate(NavigationFlow.Note(id.toString()).invoke())
}

fun NavGraphBuilder.noteGraph() = composable(
    route = NavigationFlow.Note("{id}").invoke(),
    arguments = listOf(navArgument(name = "id"){type = NavType.IntType})
) {
    NoteScreen(it.arguments?.getInt("id",0)!!)
}