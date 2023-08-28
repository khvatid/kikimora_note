package khvatid.kikimora.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import khvatid.core.ui.theme.spaceMono
import khvatid.kikimora.domain.models.MessageModel


@Composable
fun Messages(
    messages: List<MessageModel>,
    listState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
    isTyping: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = verticalArrangement,
        contentPadding = contentPadding
    ) {
        items(messages) {
            Message(
                content = it.content,
                role = it.role,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
        if (isTyping) {
            item {
                Card(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    colors = getColors(role = "assistant"),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(64.dp)
                            .height(32.dp)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingAnimation(
                            circleSize = 10.dp,
                            spaceBetween = 4.dp,
                            travelDistance = 10.dp
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Message(content: String, role: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(getPadding(role = role)),
        contentAlignment = if (role == "user") Alignment.TopEnd else Alignment.TopStart
    ) {

        Card(
            modifier = modifier,
            colors = getColors(role = role),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            val tokens = pattern.findAll(content)
            var cursorPosition = 0
            SelectionContainer(modifier = Modifier.padding(8.dp)) {
                Column {
                    tokens.forEach { token ->
                        Text(
                            text = content.slice(cursorPosition until token.range.first)
                        )
                        tokenString(value = token.value)
                        cursorPosition = token.range.last + 1
                    }
                    if (!tokens.none()) {
                        Text(text = content.slice(cursorPosition..content.lastIndex))
                    } else {
                        Text(
                            text = content,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}


val pattern by lazy {
    Regex("```[^`]+```")
}

@Composable
fun getColors(role: String): CardColors {
    return if (role == "user") CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) else {
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun getPadding(role: String): PaddingValues {
    return if (role == "user") PaddingValues(start = 24.dp)
    else PaddingValues(end = 24.dp)
}

@Composable
fun tokenString(value: String) {
    when (value.first()) {
        '`' -> {
            val scroll = rememberScrollState()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Black,
                        shape = MaterialTheme.shapes.medium
                    )
                    .horizontalScroll(state = scroll)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = value.trim('`'),
                    color = Color.White,
                    fontFamily = spaceMono,
                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle
                )
            }

        }

        else -> {
            Text(text = value)
        }
    }
}
