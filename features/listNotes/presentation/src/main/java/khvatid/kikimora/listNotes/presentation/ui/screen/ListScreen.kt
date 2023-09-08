package khvatid.kikimora.listNotes.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.kikimora.listNotes.domain.model.NoteModel

@Composable
fun ListScreen(
    navigateToNote: (Int) -> Unit,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ListScreenUi(state = state, navigateToNote = navigateToNote, event = viewModel::obtainEvent)
}

@Composable
private fun ListScreenUi(
    state: ListScreenContract.State,
    navigateToNote: (Int) -> Unit,
    event: (ListScreenContract.Events) -> Unit
) {
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
            items(state.notes) {
                NoteItemList(note = it, onClick = navigateToNote)
            }
        }
        AnimatedVisibility(visible = !listState.isScrollInProgress) {
            FloatingActionButton(onClick = { navigateToNote(0) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun NoteItemList(note: NoteModel, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(note.id) })
            .padding(horizontal = 16.dp),
    ) {
        Text(text = note.title)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = note.author)
            Text(text = note.date)
        }
    }
}

