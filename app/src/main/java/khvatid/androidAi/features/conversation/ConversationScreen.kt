package khvatid.androidAi.features.conversation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import khvatid.androidAi.R
import khvatid.androidAi.features.conversation.ConversationScreenContract.Event
import khvatid.androidAi.features.conversation.ConversationScreenContract.State
import khvatid.androidAi.ui.components.InputBar
import khvatid.androidAi.ui.components.Messages

@Composable
fun ConversationScreen(viewModel: ConversationScreenViewModel) {
    val state by viewModel.uiState.collectAsState()
    ConversationScreenUi(state = state, events = viewModel::obtainEvent)
}


@Composable
private fun ConversationScreenUi(
    state: State,
    events: (Event) -> Unit
) {
    LaunchedEffect(key1 = state.messages) {
        if (state.messages.isNotEmpty()) {
            state.listState.scrollToItem(state.messages.lastIndex)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(), contentAlignment = Alignment.BottomCenter
    ) {
        Messages(
            modifier = Modifier
                .fillMaxSize(),
            messages = state.messages,
            isTyping = state.languageModelWrite,
            listState = state.listState,
            verticalArrangement = Arrangement.Top,
            contentPadding = PaddingValues(bottom = 88.dp, top = 0.dp)
        )
        InputBar(modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 8.dp, vertical = 4.dp),
            value = state.message,
            placeholder = stringResource(id = R.string.input_placeholder),
            onValueChange = { events(Event.MessageValueChanged(it)) },
            onClick = {
                events(Event.SendMessage)
            }
        )
    }



}