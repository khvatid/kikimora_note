package khvatid.kikimora.features.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

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


   }

   sealed class Event {
      object NavigateToSettings : Event()
      object NavigateToHome : Event()
   }

}
