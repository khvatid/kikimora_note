package khvatid.kikimora.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import khvatid.kikimora.R

val spaceMono = FontFamily(
    Font(
        resId = R.font.space_mono_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    )
)

private val montserrat = FontFamily(
    Font(
        R.font.montserrat_black,
        weight = FontWeight.Black,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_extra_bold,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_extra_light,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ), Font(
        R.font.montserrat_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_semi_bold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    ),
    Font(
        R.font.montserrat_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal,
        loadingStrategy = FontLoadingStrategy.Blocking
    )
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.3).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.3).sp,
    ),
    displaySmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.3).sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.3).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.3).sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.3).sp,
    ),
    titleSmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.3).sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp,
    ),
    bodySmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.3).sp,
    ),
    labelLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.3).sp,
    ),
    labelMedium = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.3).sp,
    ),
    labelSmall = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.3).sp,
    ),
)