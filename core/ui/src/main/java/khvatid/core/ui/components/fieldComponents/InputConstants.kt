package khvatid.core.ui.components.fieldComponents

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.LayoutIdParentData
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

internal enum class InputPhase {
    Focused,
    UnfocusedEmpty,
    UnfocusedNotEmpty
}

internal val IntrinsicMeasurable.layoutId: Any?
    get() = (parentData as? LayoutIdParentData)?.layoutId

internal const val FieldComponentId = "FieldComponent"
internal const val PlaceholderId = "Hint"
internal const val TitleId = "Title"
internal const val LeadingId = "Leading"
internal const val TrailingId = "Trailing"
internal const val SupportingId = "Supporting"
internal const val ContainerId = "Container"

internal val ZeroConstraints = Constraints(0, 0, 0, 0)

internal const val AnimationDuration = 150
internal const val PlaceholderAnimationDuration = 83
internal const val PlaceholderAnimationDelayOrDuration = 67

internal val UnfocusedBorderThickness = 1.dp
internal val FocusedBorderThickness = 2.dp

internal val HorizontalIconPadding = 12.dp
internal val SupportingTopPadding = 4.dp

internal val IconDefaultSizeModifier = Modifier.defaultMinSize(48.dp, 48.dp)

internal fun widthOrZero(placeable: Placeable?) = placeable?.width ?: 0
internal fun heightOrZero(placeable: Placeable?) = placeable?.height ?: 0