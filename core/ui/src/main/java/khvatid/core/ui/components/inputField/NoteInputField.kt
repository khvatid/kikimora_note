package khvatid.core.ui.components.inputField

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun NoteInputField(

    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit = {},
    singleLine: Boolean = false,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        onTextLayout = { it },
        singleLine = singleLine,
        textStyle = textStyle,
        readOnly = readOnly,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Box {
                AnimatedVisibility(
                    visible = value.isEmpty(),
                    enter = expandHorizontally(),
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 100)
                    )
                ) {
                    placeholder()
                }
                innerTextField()
            }
        }
    )
}