package khvatid.core.ui.components.fieldComponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp

object InputFieldComponentDefaults{














    internal class InputFieldColors constructor(
        private val textColor : Color,

        private val placeholder: Color,

        private val supportedTextColor : Color,


        private val enabledTrailingContentColor : Color,
        private val disableTrailingContentColor: Color,

        private val enabledLeadingContentColor : Color,
        private val disableLeadingContentColor: Color,

        private val focusedLabelContentColor: Color,
        private val unfocusedLabelContentColor: Color,
        private val errorLabelContentColor: Color,

        private val focusedIndicator: Color,

        private val containerColor : Color,
    ){
        @Composable
        internal fun indicatorColor(
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
        internal fun supportingTextColor(
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
        internal fun titleColor(
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
        internal fun animateBorderStrokeAsState(
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
        internal fun containerBackgroundColor(
            isError: Boolean,
        ): State<Color> {
            return rememberUpdatedState(
                when {
                    isError -> MaterialTheme.colorScheme.errorContainer
                    else -> Color.Transparent
                }
            )
        }




    }
}
