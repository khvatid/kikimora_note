package khvatid.core.ui.components.fieldComponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.lerp
import androidx.compose.ui.unit.Dp

@Composable
internal fun InputDecorationBox(
    value: String,
    enabled: Boolean,
    singleLine: Boolean,
    visualTransformation: VisualTransformation,
    interactionSource: InteractionSource,
    error: Boolean,
    contentPadding: PaddingValues,
    innerTextField: @Composable () -> Unit,
    title: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    container: @Composable () -> Unit = {},

    ) {

    val transformedText = remember(value, visualTransformation) {
        visualTransformation.filter(AnnotatedString(value))
    }.text.text

    val isFocused = interactionSource.collectIsFocusedAsState().value

    val inputState = when {
        isFocused -> InputPhase.Focused
        transformedText.isEmpty() -> InputPhase.UnfocusedEmpty
        else -> InputPhase.UnfocusedNotEmpty
    }

    val labelSize = remember { mutableStateOf(Size.Zero) }
    val labelColor: @Composable (InputPhase) -> Color = {
        titleColor(enabled, error, interactionSource).value
    }

    val typography = MaterialTheme.typography
    val bodyLarge = typography.bodyLarge
    val bodySmall = typography.bodySmall
    val shouldOverrideTextStyleColor =
        (bodyLarge.color == Color.Unspecified && bodySmall.color != Color.Unspecified) ||
                (bodyLarge.color != Color.Unspecified && bodySmall.color == Color.Unspecified)

    InputComponentTransitionScope.Transition(
        inputState = inputState,
        focusedTextStyleColor = with(MaterialTheme.typography.bodyMedium.color) {
            if (shouldOverrideTextStyleColor) this.takeOrElse { labelColor(inputState) } else this
        },
        unfocusedTextStyleColor = with(MaterialTheme.typography.bodySmall.color) {
            if (shouldOverrideTextStyleColor) this.takeOrElse { labelColor(inputState) } else this
        },
        contentColor = labelColor,
        showLabel = title != null
    ) { titleProgress, titleTextStyleColor, titleContentColor, placeholderAlphaProgress ->

        val decoratedTitle: @Composable (() -> Unit)? = title?.let {
            @Composable {
                val titleTextStyle = lerp(
                    MaterialTheme.typography.bodyMedium,
                    MaterialTheme.typography.bodySmall,
                    titleProgress
                ).let {
                    if (shouldOverrideTextStyleColor) it.copy(color = titleTextStyleColor) else it
                }
                Decoration(titleContentColor, titleTextStyle, it)
            }
        }


        val defaultErrorMessage = "Default error message"
        val decorationBoxModifier: Modifier =
            Modifier.semantics { if (error) error(defaultErrorMessage) }

        val trailingIconColor = MaterialTheme.colorScheme.surfaceTint
        val decoratedTrailing: @Composable (() -> Unit)? = trailingIcon?.let {
            @Composable {
                Decoration(
                    contentColor = trailingIconColor,
                    content = it
                )
            }
        }


        val supportingTextColor =
            supportingTextColor(enabled, error, interactionSource).value
        val decoratedSupporting: @Composable (() -> Unit)? = supportingText?.let {
            @Composable {
                Decoration(
                    contentColor = supportingTextColor,
                    typography = typography.labelSmall,
                    content = it
                )
            }
        }


        val decoratedPlaceholder: @Composable (() -> Unit)? =
            if (placeholder != null && transformedText.isEmpty()) {
                @Composable {
                    Decoration(
                        contentColor = placeholderColor(enabled, placeholderAlphaProgress).value,
                        typography = MaterialTheme.typography.bodyMedium,
                        content = placeholder
                    )
                }
            } else null


        val borderContainerWithId: @Composable () -> Unit = {
            Box(
                Modifier
                    .layoutId(ContainerId)
                //.outlineCutout(labelSize.value, contentPadding)
                ,
                propagateMinConstraints = true
            ) {
                container()
            }
        }

        InputComponentLayout(
            modifier = decorationBoxModifier,
            textField = innerTextField,
            placeholder = decoratedPlaceholder,
            supporting = decoratedSupporting,
            title = decoratedTitle,
            trailing = decoratedTrailing,
            singleLine = singleLine,
            animationProgress = titleProgress,
            onLabelMeasured = {
                val labelWidth = it.width * titleProgress
                val labelHeight = it.height * titleProgress
                if (labelSize.value.width != labelWidth ||
                    labelSize.value.height != labelHeight
                ) {
                    labelSize.value = Size(labelWidth, labelHeight)
                }
            },
            container = borderContainerWithId,
            paddingValues = contentPadding
        )
    }

}

@Composable
internal fun BorderStrokeContainer(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    focusedBorderThickness: Dp,
    unfocusedBorderThickness: Dp,
    style: FocusIndicator = FocusIndicator.Outline
) {
    val borderStroke = animateBorderStrokeAsState(
        enabled = enabled,
        isError = isError,
        interactionSource = interactionSource,
        focusedBorderThickness = focusedBorderThickness,
        unfocusedBorderThickness = unfocusedBorderThickness
    )
    val background = containerBackgroundColor(isError = isError)
    Box(
        modifier = when (style) {
            FocusIndicator.Line -> Modifier
                .drawFocusedLine(indicatorBorder = borderStroke.value)
                .background(color = background.value)

            FocusIndicator.Outline -> Modifier
                .border(
                    border = borderStroke.value,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .background(color = background.value, shape = MaterialTheme.shapes.extraLarge)
        }
    )
}


@Composable
internal fun TransparentContainer(
    isError: Boolean,
) {
    val background = containerBackgroundColor(isError = isError)
    Box(
        Modifier
            .background(color = background.value)
    )
}


@Composable
private fun indicatorColor(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource
): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    val targetValue = when {
        !enabled -> MaterialTheme.colorScheme.onBackground
        isError -> MaterialTheme.colorScheme.error
        focused -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onBackground
    }
    return if (enabled) {
        animateColorAsState(
            targetValue,
            tween(durationMillis = AnimationDuration),
            label = "indicatorColor"
        )
    } else {
        rememberUpdatedState(targetValue)
    }
}


@Composable
private fun placeholderColor(enabled: Boolean, alpha: Float): State<Color> {
    return rememberUpdatedState(if (enabled) MaterialTheme.colorScheme.outline else Color.Transparent)
}

/*@Composable
internal fun trailingIconColor(
    interactionSource: InteractionSource
): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    return rememberUpdatedState(

        LightGrey60
    )
}*/

@Composable
private fun supportingTextColor(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource
): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    return rememberUpdatedState(
        when {
            !enabled -> MaterialTheme.colorScheme.onBackground
            isError -> MaterialTheme.colorScheme.error
            focused -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.onBackground
        }
    )
}

@Composable
private fun titleColor(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource
): State<Color> {
    val focused by interactionSource.collectIsFocusedAsState()

    val targetValue = when {
        !enabled -> MaterialTheme.colorScheme.onBackground
        isError -> MaterialTheme.colorScheme.error
        focused -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onBackground
    }
    return rememberUpdatedState(targetValue)
}


@Composable
private fun animateBorderStrokeAsState(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    focusedBorderThickness: Dp,
    unfocusedBorderThickness: Dp
): State<BorderStroke> {
    val focused by interactionSource.collectIsFocusedAsState()
    val indicatorColor = indicatorColor(enabled, isError, interactionSource)
    val targetThickness = if (focused) focusedBorderThickness else unfocusedBorderThickness
    val animatedThickness = if (enabled) {
        animateDpAsState(
            targetThickness,
            tween(durationMillis = AnimationDuration),
            label = "borderStroke"
        )
    } else {
        rememberUpdatedState(unfocusedBorderThickness)
    }
    return rememberUpdatedState(
        BorderStroke(animatedThickness.value, SolidColor(indicatorColor.value))
    )
}


@Composable
private fun containerBackgroundColor(
    isError: Boolean,
): State<Color> {
    return rememberUpdatedState(
        when {
            isError -> MaterialTheme.colorScheme.errorContainer
            else -> Color.Transparent
        }
    )
}


@Composable
private fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit
) {
    val contentWithColor: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content
        )
    }
    if (typography != null) ProvideTextStyle(typography, contentWithColor) else contentWithColor()
}


internal fun Modifier.drawFocusedLine(indicatorBorder: BorderStroke): Modifier {
    return drawWithContent {
        val strokeWidthDp = indicatorBorder.width
        drawContent()
        if (strokeWidthDp == Dp.Hairline) return@drawWithContent
        val strokeWidth = strokeWidthDp.value * density
        val y = 0f + strokeWidth / 2
        drawLine(
            indicatorBorder.brush,
            Offset(0f + strokeWidth, size.height - strokeWidth / 2),
            Offset(0f + strokeWidth, y),
            strokeWidth
        )
    }
}