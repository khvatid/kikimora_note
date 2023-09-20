package khvatid.core.ui.components.fieldComponents

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khvatid.core.ui.theme.CoreTheme


@Composable
fun InputText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    withTitle: Boolean = false,
    error: Boolean = false,
    isEnabled: Boolean = true,
    supportingText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = 5,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    trailingContent: @Composable () -> Unit = {},
) {

    BasicTextField(
        modifier = if (withTitle) {
            modifier
                .semantics(mergeDescendants = true) {}
                .padding(top = 20.dp)
        } else {
            modifier
        }.defaultMinSize(
            minWidth = 280.dp,
            minHeight = 44.dp
        ),
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        interactionSource = interactionSource,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        onTextLayout = {

        }
    ) { innerTextField ->

        InputDecorationBox(
            value = value,
            enabled = isEnabled,
            singleLine = singleLine,
            error = error,
            innerTextField = innerTextField,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            title = if (withTitle) {
                { Text(text = placeholder) }
            } else null,
            placeholder = if (!withTitle) {
                { Text(text = placeholder) }
            } else null,
            trailingIcon = trailingContent,
            supportingText = { Text(text = supportingText.orEmpty()) },
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            container = {
                BorderStrokeContainer(
                    enabled = isEnabled,
                    isError = error,
                    interactionSource = interactionSource,
                    focusedBorderThickness = FocusedBorderThickness,
                    unfocusedBorderThickness = UnfocusedBorderThickness,
                )
            }
        )
    }


}


@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    error: Boolean = false,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    maxLines: Int = 5,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    placeholder: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    BasicTextField(
        modifier = modifier.defaultMinSize(minWidth = 280.dp, minHeight = 44.dp),
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        interactionSource = interactionSource,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
    ) { innerTextField ->

        InputDecorationBox(
            value = value,
            enabled = isEnabled,
            singleLine = singleLine,
            error = error,
            innerTextField = innerTextField,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            placeholder = placeholder,
            trailingIcon = trailingContent,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            container = {
                TransparentContainer(
                    isError = error
                )
            }
        )
    }
}

sealed class FocusIndicator{
    object Line : FocusIndicator()
    object Outline : FocusIndicator()
}

@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    error: Boolean = false,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = 5,
    readOnly: Boolean = false,
    focusIndicator : FocusIndicator = FocusIndicator.Outline,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null
) {
    BasicTextField(
        modifier = if (title != null) {
            modifier
                .semantics(mergeDescendants = true) {}
                .padding(top = 20.dp)
        } else {
            modifier
        }.defaultMinSize(
            minWidth = 280.dp,
            minHeight = 44.dp
        ),
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        interactionSource = interactionSource,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
    ) { innerTextField ->

        InputDecorationBox(
            value = value,
            enabled = isEnabled,
            singleLine = singleLine,
            error = error,
            innerTextField = innerTextField,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            title = title,
            placeholder = placeholder,
            trailingIcon = trailingContent,
            leadingIcon = leadingContent,
            supportingText = supportingText,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            container = {
                BorderStrokeContainer(
                    style = focusIndicator,
                    enabled = isEnabled,
                    isError = error,
                    interactionSource = interactionSource,
                    focusedBorderThickness = FocusedBorderThickness,
                    unfocusedBorderThickness = UnfocusedBorderThickness,
                )
            }
        )
    }


}


@Preview
@Composable
private fun Preview() {
    var value by remember {
        mutableStateOf("")
    }
    CoreTheme(darkTheme = true) {
        Surface(

        ) {
            Column(modifier = Modifier.padding(top = 100.dp)) {
                TransparentTextField(
                    value = value,
                    onValueChange = {
                        value = it
                    }
                )

                InputText(
                    value = value,
                    onValueChange = { value = it },
                    placeholder = "Title",
                    withTitle = true,
                    )
                // OutlineInputField()
            }

        }
    }
}