package khvatid.kikimora.features.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import khvatid.kikimora.features.app.navigation.AppDestination

interface AppContract {

    @Stable
    data class State(
        val isDynamicTheme: Boolean = false,
        val navController: NavHostController,

        ) {
        val currentRoute: String
            @Composable get() =
                navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

        val route: String get() = navController.currentBackStackEntry?.destination?.route ?: ""

        val isShowBottomBar: Boolean
            @Composable get() = when (currentRoute) {
                AppDestination.Settings() -> true
                AppDestination.NoteList() -> true
                AppDestination.ConversationList() -> true
                else -> false
            }

        val tabs: List<AppDestination.NoArgumentsDestination> = listOf(
            AppDestination.NoteList,
            AppDestination.ConversationList,
            AppDestination.Settings
        )
    }

    sealed class Event {
        data class BottomBarClick(val route: String) : Event()
        data class NavigateToConversation(val id: String) : Event()
    }

}
