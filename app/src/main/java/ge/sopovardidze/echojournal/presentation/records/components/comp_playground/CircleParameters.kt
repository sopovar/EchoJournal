package ge.sopovardidze.echojournal.presentation.records.components.comp_playground

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R

data class CircleParameters(
    val radius: Dp = 16.dp,
    val backgroundColor: Color? = null,
    val stroke: StrokeParameters? = null,
    @DrawableRes val icon: Int? = R.drawable.ic_mood_sad
)