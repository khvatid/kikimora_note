package khvatid.kikimora.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import khvatid.core.navigation.NavigationFlow
import khvatid.core.ui.theme.CoreTheme
import khvatid.kikimora.listNotes.presentation.navigation.listNotesGraph
import khvatid.kikimora.note.presentation.navigation.navigateToNoteScreen
import khvatid.kikimora.note.presentation.navigation.noteGraph


data class AppScreenState(
    val navController: NavHostController,
    val startDestination: NavigationFlow = NavigationFlow.ListNotes,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(appViewModel: AppViewModel) {
    val state by appViewModel.state.collectAsState()
    CoreTheme {
        Scaffold {
            NavHost(
                modifier = Modifier.padding(it),
                navController = state.navController,
                startDestination = state.startDestination()
            ) {
                listNotesGraph(navigateToNote = state.navController::navigateToNoteScreen)
                noteGraph()
            }
        }
    }
}