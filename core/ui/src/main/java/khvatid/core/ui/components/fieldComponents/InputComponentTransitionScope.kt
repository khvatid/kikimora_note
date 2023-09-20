package khvatid.core.ui.components.fieldComponents

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

internal object InputComponentTransitionScope {
    @Composable
    fun Transition(
        inputState: InputPhase,
        focusedTextStyleColor: Color,
        unfocusedTextStyleColor: Color,
        contentColor: @Composable (InputPhase) -> Color,
        showLabel: Boolean,
        content: @Composable (
            labelProgress: Float,
            labelTextStyleColor: Color,
            labelContentColor: Color,
            placeholderOpacity: Float
        ) -> Unit
    ) {
        val transition = updateTransition(inputState, label = "TextFieldInputState")

        val titleProgress by transition.animateFloat(
            label = "TitleProgress",
            transitionSpec = { tween(durationMillis = AnimationDuration) }
        ) {
            when (it) {
                InputPhase.Focused -> 1f
                InputPhase.UnfocusedEmpty -> 0f
                InputPhase.UnfocusedNotEmpty -> 1f
            }
        }

        val placeholderOpacity by transition.animateFloat(
            label = "PlaceholderOpacity",
            transitionSpec = {
                if (InputPhase.Focused isTransitioningTo InputPhase.UnfocusedEmpty) {
                    tween(
                        durationMillis = PlaceholderAnimationDelayOrDuration,
                        easing = LinearEasing
                    )
                } else if (InputPhase.UnfocusedEmpty isTransitioningTo InputPhase.Focused ||
                    InputPhase.UnfocusedNotEmpty isTransitioningTo InputPhase.UnfocusedEmpty
                ) {
                    tween(
                        durationMillis = PlaceholderAnimationDuration,
                        delayMillis = PlaceholderAnimationDelayOrDuration,
                        easing = LinearEasing
                    )
                } else {
                    spring()
                }
            }
        ) {
            when (it) {
                InputPhase.Focused -> 0f
                InputPhase.UnfocusedEmpty -> if (showLabel) 0f else 1f
                InputPhase.UnfocusedNotEmpty -> 0f
            }
        }

        val titleTextStyleColor by transition.animateColor(
            transitionSpec = { tween(durationMillis = AnimationDuration) },
            label = "TitleTextStyleColor"
        ) {
            when (it) {
                InputPhase.Focused -> focusedTextStyleColor
                else -> unfocusedTextStyleColor
            }
        }

        val titleContentColor by transition.animateColor(
            transitionSpec = { tween(durationMillis = AnimationDuration) },
            label = "TitleContentColor",
            targetValueByState = contentColor
        )

        content(
            titleProgress,
            titleTextStyleColor,
            titleContentColor,
            placeholderOpacity
        )
    }
}