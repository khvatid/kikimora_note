package khvatid.core.ui.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class MaskVisualTransformation(
    private val mask: String,
    private val emptyMask: String,
    private val specialSymbol: Char = '#'
) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != specialSymbol }

    override fun filter(text: AnnotatedString): TransformedText {
        if (text.text.isEmpty()){ return TransformedText(AnnotatedString(emptyMask),offsetTranslator()) }
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return emptyMask.length
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == specialSymbol) numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == specialSymbol }
        }
    }
}
