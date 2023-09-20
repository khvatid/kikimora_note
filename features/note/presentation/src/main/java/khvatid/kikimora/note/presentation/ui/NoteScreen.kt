package khvatid.kikimora.note.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.core.ui.components.clearFocusOnKeyboardDismiss
import khvatid.core.ui.components.fieldComponents.TransparentInputField
import khvatid.kikimora.note.presentation.ui.NoteScreenContract.Events

@Composable
internal fun NoteScreen(
    id: Int,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.obtainEvent(event = Events.OnLaunch(id))
    }
    ScreenUi(
        state = state,
        event = viewModel::obtainEvent
    )
}

@Composable
private fun ScreenUi(
    state: NoteScreenContract.State,
    event: (Events) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(0.1f, false),
                onClick = { event(Events.OnAccept) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline
                )
            }
            /*TransparentInputField(
                modifier = Modifier
                    .clearFocusOnKeyboardDismiss()
                    .weight(0.9f, fill = true),
                value = state.title,
                singleLine = true,
                textStyle = titleStyle
                    .copy(MaterialTheme.colorScheme.primary),
                onValueChange = { event(Events.OnTitleTextChange(it)) },
                placeholder = {
                    Text(
                        text = "Title",
                        style = titleStyle
                            .copy(MaterialTheme.colorScheme.outline)
                    )
                },
            )*/
        }
        /*TransparentInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clearFocusOnKeyboardDismiss(),
            value = state.content.text,
            textStyle = contentTextStyle
                .copy(MaterialTheme.colorScheme.primary),
            onValueChange = { event(Events.OnContentTextChange(it)) },
            placeholder = {
                Text(
                    text = "Enter text...",
                    style = contentTextStyle
                        .copy(MaterialTheme.colorScheme.outline)
                )
            }
        )*/
    }

}

private val titleStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.typography.displaySmall

private val contentTextStyle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.typography.bodyLarge
/*

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
}*/
