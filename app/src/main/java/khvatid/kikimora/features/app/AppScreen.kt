package khvatid.kikimora.features.app

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import khvatid.kikimora.features.app.navigation.AppDestination
import khvatid.kikimora.features.app.navigation.appNavigationGraph
import khvatid.kikimora.ui.theme.AiTheme

@Composable
fun AppScreen(viewModel: AppViewModel) {
   val state by viewModel.uiState.collectAsState()
   AiTheme(dynamicColor = state.isDynamicTheme) {
      AppScreenUi(state = state, events = viewModel::obtainEvent)
   }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun AppScreenUi(state: AppContract.State, events: (AppContract.Event) -> Unit) {
   Scaffold(
      bottomBar = {},
      contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
      content = {
         NavHost(
            modifier = Modifier
               .fillMaxSize()
               .padding(it)
               .consumeWindowInsets(it),
            navController = state.navController,
            startDestination = AppDestination.ConversationList(),
         ) {
            appNavigationGraph(
               navigateToConversation = { id ->
                  events(AppContract.Event.NavigateToConversation(id))
               }
            )
         }
      }
   )
}