package khvatid.kikimora.features.conversations.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import khvatid.kikimora.features.conversations.list.ConversationListScreenContract.Event
import khvatid.kikimora.features.conversations.list.ConversationListScreenContract.State

@Composable
fun ConversationListScreen(
   viewModel: ConversationListScreenViewModel,
   navigateToConversation: (String) -> Unit
) {
   val state by viewModel.uiState.collectAsState()
   ConversationListScreenUi(state = state, events = viewModel::obtainEvent, navigateToConversation)
}


@Composable
private fun ConversationListScreenUi(
   state: State,
   events: (Event) -> Unit,
   navigateToConversation: (String) -> Unit
) {
   LazyColumn(
      content = {
         items(state.listConversation) {
            Row(modifier = Modifier
               .fillMaxWidth()
               .clickable { navigateToConversation(it) }) {
               Text(text = it)
            }
         }
      }
   )


}