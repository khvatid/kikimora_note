package khvatid.kikimora.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun InputBar(
    modifier: Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        InputTextField(
            modifier = Modifier
                .weight(0.9f)
                .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.large),
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder
        )
        AnimatedVisibility(visible = value.isNotEmpty()) {
            InputButton(modifier = Modifier.weight(0.1f).padding(start = 4.dp), value.isNotEmpty(), onClick = onClick)

        }
    }
}


@Composable
private fun InputButton(modifier: Modifier, enabled: Boolean, onClick: () -> Unit) {
    IconButton(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background, CircleShape)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 1.0f),
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
                ),
                CircleShape
            ),
        enabled = enabled,
        onClick = onClick,
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onPrimary,
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    BasicTextField(modifier = modifier,
        value = value,
        maxLines = 5,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start
        ),
        interactionSource = interactionSource,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        visualTransformation = visualTransformation,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                innerTextField = {
                    Box(
                        modifier = Modifier.defaultMinSize(minHeight = 32.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                    }
                },
                enabled = enabled,
                singleLine = true,
                visualTransformation = visualTransformation,
                placeholder = { Text(text = placeholder) },
                interactionSource = interactionSource,
                container = {
                    TextFieldDefaults.OutlinedBorderContainerBox(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.outlinedTextFieldColors(),
                        shape = MaterialTheme.shapes.large
                    )
                },
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    )
}
