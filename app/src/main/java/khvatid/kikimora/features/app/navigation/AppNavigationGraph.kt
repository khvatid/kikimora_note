package khvatid.kikimora.features.app.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import khvatid.kikimora.features.conversation.ConversationScreen
import khvatid.kikimora.features.notes.NotesScreen
import khvatid.kikimora.features.settings.SettingsScreen


fun NavGraphBuilder.appNavigationGraph() {
   composable(route = AppDestination.Settings()) {
      SettingsScreen(viewModel = hiltViewModel())
   }
   composable(route = AppDestination.ListNotes()) {
      NotesScreen(viewModel = hiltViewModel())
   }
   composable(
      route = AppDestination.Conversation(id = "{id}").fullRoute,
      arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
   ) {
      ConversationScreen(viewModel = hiltViewModel())
   }
   composable(route = AppDestination.ListConversations()) {

   }
}