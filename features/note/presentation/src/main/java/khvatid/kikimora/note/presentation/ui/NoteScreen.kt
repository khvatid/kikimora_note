package khvatid.kikimora.note.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import khvatid.core.ui.components.clearFocusOnKeyboardDismiss
import khvatid.kikimora.note.domain.models.ContentModel

@Composable
internal fun NoteScreen(
    viewModel: NoteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ScreenUi(
        state = state,
        event = viewModel::obtainEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenUi(
    state: NoteScreenContract.State,
    event: (NoteScreenContract.Events) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(
            modifier = Modifier,
        ) {
            itemsIndexed(state.content) { index, model ->
                when (model) {
                    is ContentModel.Photo -> PhotoBox(contentModel = model)
                    is ContentModel.Text -> TextBox(
                        contentModel = model,
                        onContentChange = {
                            event(
                                NoteScreenContract.Events.ChangeContentText(
                                    it,
                                    index
                                )
                            )
                        }
                    )
                }
            }
            item {
                ListMenu()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
            OutlinedTextField(value = state.title, onValueChange = {})
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = null)
            }
        }

    }
}

@Composable
fun TextBox(
    contentModel: ContentModel.Text,
    onContentChange: (ContentModel.Text) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clearFocusOnKeyboardDismiss()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            value = contentModel.text,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
            onValueChange = { onContentChange(contentModel.copy(text = it)) },
            onTextLayout = { it },
            keyboardActions = KeyboardActions(),
            cursorBrush = Brush.verticalGradient(
                listOf(
                    MaterialTheme.colorScheme.onPrimaryContainer,
                    MaterialTheme.colorScheme.primary
                )
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            decorationBox = { innerTextField ->
                innerTextField()
            }

        )
    }
}


@Composable
fun PhotoBox(contentModel: ContentModel.Photo) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        AsyncImage(
            model = contentModel.path,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }
}


@Composable
fun ListMenu() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Create, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Face, contentDescription = null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Phone, contentDescription = null)
        }
    }
}
