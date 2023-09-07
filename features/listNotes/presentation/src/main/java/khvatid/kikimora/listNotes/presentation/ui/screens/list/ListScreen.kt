package khvatid.kikimora.listNotes.presentation.ui.screens.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.kikimora.listNotes.domain.model.NoteModel

@Composable
fun ListScreen(
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ListScreenUi(state = state, event = viewModel::obtainEvent)
}

@Composable
private fun ListScreenUi(
    state: ListScreenContract.State,
    event: (ListScreenContract.Events) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(state.notes){
            NoteItemList(note = it)
        }
    }
}

@Composable
fun NoteItemList(note: NoteModel){
    Row {

    }
}

