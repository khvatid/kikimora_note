package khvatid.androidAi.app

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

interface AppContract{

    @Stable
    data class State(
        val isDynamicTheme : Boolean = false,
        val navController: NavHostController,
    )

    sealed class Event{
        object NavigateToSettings: Event()
        object NavigateToHome: Event()
    }

}
