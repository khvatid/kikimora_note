package khvatid.kikimora.features.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotesScreen(viewModel: NotesScreenViewModel) {
    val state by viewModel.uiState.collectAsState()
    NotesScreenUi(state = state, events = viewModel::obtainEvent)
}


@Composable
private fun NotesScreenUi(
    state: NotesScreenContract.State,
    events: (NotesScreenContract.Event) -> Unit
) {

}