package khvatid.core.ui.components.fieldComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
internal fun InputComponentLayout(
    modifier: Modifier,
    textField: @Composable () -> Unit,
    placeholder: @Composable (() -> Unit)?,
    supporting: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    trailing: @Composable (() -> Unit)?,
    singleLine: Boolean,
    animationProgress: Float,
    onLabelMeasured: (Size) -> Unit,
    container: @Composable () -> Unit,
    paddingValues: PaddingValues
) {
    val measurePolicy = remember(onLabelMeasured, singleLine, animationProgress, paddingValues) {
        InputComponentMeasurePolicy(
            onLabelMeasured,
            singleLine,
            animationProgress,
            paddingValues
        )
    }
    val layoutDirection = LocalLayoutDirection.current
    Layout(
        modifier = modifier,
        content = {
            container()
            if (trailing != null) {
                Box(
                    modifier = Modifier
                        .layoutId(TrailingId)
                        .then(
                            IconDefaultSizeModifier
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    trailing()
                }
            }
            val startInputPadding = paddingValues.calculateStartPadding(layoutDirection)
            val endInputPadding = paddingValues.calculateEndPadding(layoutDirection)
            val padding = Modifier.padding(
                start = startInputPadding,
                end = if (trailing != null) {
                    (endInputPadding - HorizontalIconPadding).coerceAtLeast(0.dp)
                } else {
                    endInputPadding
                }
            )
            if (placeholder != null) {
                Box(
                    modifier = Modifier
                        .then(padding)
                        .layoutId(PlaceholderId)
                ) {
                    placeholder()
                }
            }
            Box(
                modifier = Modifier
                    .layoutId(FieldComponentId)
                    .then(padding),
                propagateMinConstraints = true,
                contentAlignment = Alignment.CenterStart
            ) {
                textField()
            }
            if (title != null) {
                Box(modifier = Modifier.layoutId(TitleId)) { title() }
            }
            if (supporting != null) {
                Box(
                    Modifier
                        .layoutId(SupportingId)
                        .padding(top = SupportingTopPadding)
                ) { supporting() }
            }
        },
        measurePolicy = measurePolicy
    )
}



internal class InputComponentMeasurePolicy(
    private val onLabelMeasured: (Size) -> Unit,
    private val singleLine: Boolean,
    private val animationProgress: Float,
    private val paddingValues: PaddingValues
) : MeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        var occupiedSpaceHorizontally = 0
        var occupiedSpaceVertically = 0
        val bottomPadding = paddingValues.calculateBottomPadding().roundToPx()

        val relaxedConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        // measure leading icon
        val leadingPlaceable = measurables.find {
            it.layoutId == LeadingId
        }?.measure(relaxedConstraints)
        occupiedSpaceHorizontally += widthOrZero(
            leadingPlaceable
        )
        occupiedSpaceVertically = max(occupiedSpaceVertically, heightOrZero(leadingPlaceable))

        // measure trailing icon
        val trailingPlaceable = measurables.find { it.layoutId == TrailingId }
            ?.measure(relaxedConstraints.offset(horizontal = -occupiedSpaceHorizontally))
        occupiedSpaceHorizontally += widthOrZero(
            trailingPlaceable
        )
        occupiedSpaceVertically = max(occupiedSpaceVertically, heightOrZero(trailingPlaceable))

        // measure label
        val isLabelInMiddleSection = animationProgress < 1f
        val labelHorizontalPaddingOffset =
            paddingValues.calculateLeftPadding(layoutDirection).roundToPx() +
                    paddingValues.calculateRightPadding(layoutDirection).roundToPx()
        val labelConstraints = relaxedConstraints.offset(
            horizontal = if (isLabelInMiddleSection) {
                -occupiedSpaceHorizontally - labelHorizontalPaddingOffset
            } else {
                -labelHorizontalPaddingOffset
            },
            vertical = - bottomPadding
        )
        val labelPlaceable =
            measurables.find { it.layoutId == TitleId }?.measure(labelConstraints)
        labelPlaceable?.let {
            onLabelMeasured(Size(it.width.toFloat(), it.height.toFloat()))
        }


        // measure text field
        // On top, we offset either by default padding or by label's half height if its too big.
        // On bottom, we offset to make room for supporting text.
        // minHeight must not be set to 0 due to how foundation TextField treats zero minHeight.
        val topPadding = max(
            heightOrZero(labelPlaceable) / 2,
            paddingValues.calculateTopPadding().roundToPx()
        )
        val textConstraints = constraints.offset(
            horizontal = -occupiedSpaceHorizontally,
            vertical = -bottomPadding - topPadding
        ).copy(minHeight = 0)
        val textFieldPlaceable =
            measurables.first { it.layoutId == FieldComponentId }.measure(textConstraints)

        // measure placeholder
        val placeholderConstraints = textConstraints.copy(minWidth = 0)
        val placeholderPlaceable =
            measurables.find { it.layoutId == PlaceholderId }?.measure(placeholderConstraints)

        occupiedSpaceVertically = max(
            occupiedSpaceVertically,
            max(heightOrZero(textFieldPlaceable), heightOrZero(placeholderPlaceable)) +
                    topPadding + bottomPadding
        )

        // measure supporting text
        val supportingConstraints = relaxedConstraints.offset(
            vertical = -occupiedSpaceVertically
        ).copy(minHeight = 0)
        val supportingPlaceable =
            measurables.find { it.layoutId == SupportingId }?.measure(supportingConstraints)
        val supportingHeight = heightOrZero(supportingPlaceable)

        val width =
            calculateWidth(
                leadingPlaceableWidth = widthOrZero(leadingPlaceable),
                trailingPlaceableWidth = widthOrZero(trailingPlaceable),
                textFieldPlaceableWidth = textFieldPlaceable.width,
                labelPlaceableWidth = widthOrZero(labelPlaceable),
                placeholderPlaceableWidth = widthOrZero(placeholderPlaceable),
                isLabelInMiddleSection = isLabelInMiddleSection,
                constraints = constraints,
                density = density,
                paddingValues = paddingValues,
            )
        val totalHeight =
            calculateHeight(
                heightOrZero(leadingPlaceable),
                heightOrZero(trailingPlaceable),
                textFieldPlaceable.height,
                heightOrZero(labelPlaceable),
                heightOrZero(placeholderPlaceable),
                heightOrZero(supportingPlaceable),
                constraints,
                density,
                paddingValues
            )
        val height = totalHeight - supportingHeight

        val containerPlaceable = measurables.first { it.layoutId == ContainerId }.measure(
            Constraints(
                minWidth = if (width != Constraints.Infinity) width else 0,
                maxWidth = width,
                minHeight = if (height != Constraints.Infinity) height else 0,
                maxHeight = height
            )
        )
        return layout(width, totalHeight) {
            place(
                totalHeight,
                width,
                leadingPlaceable,
                trailingPlaceable,
                textFieldPlaceable,
                labelPlaceable,
                placeholderPlaceable,
                containerPlaceable,
                supportingPlaceable,
                animationProgress,
                singleLine,
                density,
                layoutDirection,
                paddingValues
            )
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(
        measurables: List<IntrinsicMeasurable>,
        width: Int
    ): Int {
        return intrinsicHeight(measurables, width) { intrinsicMeasurable, w ->
            intrinsicMeasurable.maxIntrinsicHeight(w)
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(
        measurables: List<IntrinsicMeasurable>,
        width: Int
    ): Int {
        return intrinsicHeight(measurables, width) { intrinsicMeasurable, w ->
            intrinsicMeasurable.minIntrinsicHeight(w)
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(
        measurables: List<IntrinsicMeasurable>,
        height: Int
    ): Int {
        return intrinsicWidth(measurables, height) { intrinsicMeasurable, h ->
            intrinsicMeasurable.maxIntrinsicWidth(h)
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicWidth(
        measurables: List<IntrinsicMeasurable>,
        height: Int
    ): Int {
        return intrinsicWidth(measurables, height) { intrinsicMeasurable, h ->
            intrinsicMeasurable.minIntrinsicWidth(h)
        }
    }


    private fun IntrinsicMeasureScope.intrinsicWidth(
        measurables: List<IntrinsicMeasurable>,
        height: Int,
        intrinsicMeasurer: (IntrinsicMeasurable, Int) -> Int
    ): Int {
        val textFieldWidth =
            intrinsicMeasurer(measurables.first { it.layoutId == FieldComponentId }, height)
        val labelWidth = measurables.find { it.layoutId == TitleId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val trailingWidth = measurables.find { it.layoutId == TrailingId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val leadingWidth = measurables.find { it.layoutId == LeadingId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val placeholderWidth = measurables.find { it.layoutId == PlaceholderId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        return calculateWidth(
            leadingPlaceableWidth = leadingWidth,
            trailingPlaceableWidth = trailingWidth,
            textFieldPlaceableWidth = textFieldWidth,
            labelPlaceableWidth = labelWidth,
            placeholderPlaceableWidth = placeholderWidth,
            isLabelInMiddleSection = animationProgress < 1f,
            constraints = ZeroConstraints,
            density = density,
            paddingValues = paddingValues,
        )
    }

    private fun IntrinsicMeasureScope.intrinsicHeight(
        measurables: List<IntrinsicMeasurable>,
        width: Int,
        intrinsicMeasurer: (IntrinsicMeasurable, Int) -> Int
    ): Int {
        val textFieldHeight =
            intrinsicMeasurer(measurables.first { it.layoutId == FieldComponentId }, width)
        val labelHeight = measurables.find { it.layoutId == TitleId }?.let {
            intrinsicMeasurer(it, width)
        } ?: 0
        val trailingHeight = measurables.find { it.layoutId == TrailingId }?.let {
            intrinsicMeasurer(it, width)
        } ?: 0
        val leadingHeight = measurables.find { it.layoutId == LeadingId }?.let {
            intrinsicMeasurer(it, width)
        } ?: 0
        val placeholderHeight = measurables.find { it.layoutId == PlaceholderId }?.let {
            intrinsicMeasurer(it, width)
        } ?: 0
        val supportingHeight = measurables.find { it.layoutId == SupportingId }?.let {
            intrinsicMeasurer(it, width)
        } ?: 0
        return calculateHeight(
            leadingPlaceableHeight = leadingHeight,
            trailingPlaceableHeight = trailingHeight,
            textFieldPlaceableHeight = textFieldHeight,
            labelPlaceableHeight = labelHeight,
            placeholderPlaceableHeight = placeholderHeight,
            supportingPlaceableHeight = supportingHeight,
            constraints = ZeroConstraints,
            density = density,
            paddingValues = paddingValues
        )
    }


    private fun calculateWidth(
        leadingPlaceableWidth: Int,
        trailingPlaceableWidth: Int,
        textFieldPlaceableWidth: Int,
        labelPlaceableWidth: Int,
        placeholderPlaceableWidth: Int,
        isLabelInMiddleSection: Boolean,
        constraints: Constraints,
        density: Float,
        paddingValues: PaddingValues,
    ): Int {
        val middleSection = maxOf(
            textFieldPlaceableWidth,
            if (isLabelInMiddleSection) labelPlaceableWidth else 0,
            placeholderPlaceableWidth
        )
        val wrappedWidth =
            leadingPlaceableWidth + middleSection + trailingPlaceableWidth
        val focusedLabelWidth =
            if (!isLabelInMiddleSection) {
                // Actual LayoutDirection doesn't matter; we only need the sum
                val labelHorizontalPadding = (paddingValues.calculateLeftPadding(LayoutDirection.Ltr) +
                        paddingValues.calculateRightPadding(LayoutDirection.Ltr)).value * density
                labelPlaceableWidth + labelHorizontalPadding.roundToInt()
            } else {
                0
            }
        return maxOf(wrappedWidth, focusedLabelWidth, constraints.minWidth)
    }


    private fun calculateHeight(
        leadingPlaceableHeight: Int,
        trailingPlaceableHeight: Int,
        textFieldPlaceableHeight: Int,
        labelPlaceableHeight: Int,
        placeholderPlaceableHeight: Int,
        supportingPlaceableHeight: Int,
        constraints: Constraints,
        density: Float,
        paddingValues: PaddingValues
    ): Int {
        // middle section is defined as a height of the text field or placeholder (whichever is
        // taller) plus 16.dp or half height of the label if it is taller, given that the label
        // is vertically centered to the top edge of the resulting text field's container
        val inputFieldHeight = max(
            textFieldPlaceableHeight,
            placeholderPlaceableHeight
        )
        val topPadding = paddingValues.calculateTopPadding().value * density
        val bottomPadding = paddingValues.calculateBottomPadding().value * density
        val middleSectionHeight = inputFieldHeight + bottomPadding + max(
            topPadding,
            labelPlaceableHeight / 2f
        )
        return max(
            constraints.minHeight,
            maxOf(
                leadingPlaceableHeight,
                trailingPlaceableHeight,
                middleSectionHeight.roundToInt()
            ) + supportingPlaceableHeight
        )
    }

    private fun Placeable.PlacementScope.place(
        totalHeight: Int,
        width: Int,
        leadingPlaceable: Placeable?,
        trailingPlaceable: Placeable?,
        textFieldPlaceable: Placeable,
        labelPlaceable: Placeable?,
        placeholderPlaceable: Placeable?,
        containerPlaceable: Placeable,
        supportingPlaceable: Placeable?,
        animationProgress: Float,
        singleLine: Boolean,
        density: Float,
        layoutDirection: LayoutDirection,
        paddingValues: PaddingValues
    ) {
        // place container
        containerPlaceable.place(IntOffset.Zero)

        // Most elements should be positioned w.r.t the text field's "visual" height, i.e., excluding
        // the supporting text on bottom
        val height = totalHeight - heightOrZero(supportingPlaceable)
        val topPadding = (paddingValues.calculateTopPadding().value * density).roundToInt()
        val startPadding =
            (paddingValues.calculateStartPadding(layoutDirection).value * density).roundToInt()

        val iconPadding = HorizontalIconPadding.value * density

        // placed center vertically and to the start edge horizontally
        leadingPlaceable?.placeRelative(
            0,
            Alignment.CenterVertically.align(leadingPlaceable.height, height)
        )

        // placed center vertically and to the end edge horizontally
        trailingPlaceable?.placeRelative(
            width - trailingPlaceable.width,
            Alignment.CenterVertically.align(trailingPlaceable.height, height)
        )

        // label position is animated
        // in single line text field label is centered vertically before animation starts
        labelPlaceable?.let {
            val startPositionY = if (singleLine) {
                Alignment.CenterVertically.align(it.height, height)
            } else {
                topPadding
            }

            val positionY =
                startPositionY * (1 - animationProgress) - (it.height) * animationProgress
            val positionX = startPadding * (1 - animationProgress)

            it.placeRelative(positionX.roundToInt(), positionY.roundToInt())
        }

        // placed center vertically and after the leading icon horizontally if single line text field
        // placed to the top with padding for multi line text field
        val textVerticalPosition = max(
            if (singleLine) {
                Alignment.CenterVertically.align(textFieldPlaceable.height, height)
            } else {
                topPadding
            },
            heightOrZero(labelPlaceable) / 2
        )
        textFieldPlaceable.placeRelative(widthOrZero(leadingPlaceable), textVerticalPosition)

        // placed similar to the input text above
        placeholderPlaceable?.let {
            val placeholderVerticalPosition = if (singleLine) {
                Alignment.CenterVertically.align(it.height, height)
            } else {
                topPadding
            }
            it.placeRelative(widthOrZero(leadingPlaceable), placeholderVerticalPosition)
        }

        // place supporting text
        supportingPlaceable?.placeRelative(0, height)
    }



}