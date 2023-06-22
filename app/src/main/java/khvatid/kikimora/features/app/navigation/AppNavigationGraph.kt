package khvatid.kikimora.features.app.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import khvatid.kikimora.features.conversations.ConversationScreen
import khvatid.kikimora.features.conversations.list.ConversationListScreen
import khvatid.kikimora.features.notes.NotesScreen
import khvatid.kikimora.features.settings.SettingsScreen


fun NavGraphBuilder.appNavigationGraph(
   navigateToConversation: (String) -> Unit
) {
   composable(route = AppDestination.Settings()) {
      SettingsScreen(viewModel = hiltViewModel())
   }
   composable(route = AppDestination.NoteList()) {
      NotesScreen(viewModel = hiltViewModel())
   }
   composable(
      route = AppDestination.Conversation(id = "{id}").fullRoute,
      arguments = listOf(navArgument(name = "id") { type = NavType.StringType })
   ) {
      ConversationScreen(viewModel = hiltViewModel())
   }
   composable(route = AppDestination.ConversationList()) {
      ConversationListScreen(
         viewModel = hiltViewModel(),
         navigateToConversation = navigateToConversation
      )
   }
}