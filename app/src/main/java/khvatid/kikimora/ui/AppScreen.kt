package khvatid.kikimora.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import khvatid.core.navigation.NavigationFlow
import khvatid.core.ui.theme.CoreTheme


data class AppScreenState(
    val navController: NavHostController,
    val startDestination: NavigationFlow = NavigationFlow.Notes,
)

@Composable
fun AppScreen(appViewModel: AppViewModel) {
    val state by appViewModel.state.collectAsState()
    CoreTheme {
        NavHost(navController = state.navController, startDestination = state.startDestination()) {

        }
    }
}