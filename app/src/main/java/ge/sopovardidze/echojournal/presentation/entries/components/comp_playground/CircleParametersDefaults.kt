package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R

object CircleParametersDefaults {

    private val defaultCircleRadius = 16.dp

    fun circleParameters(
        radius: Dp = defaultCircleRadius,
        backgroundColor: Color = Color.Cyan,
        stroke: StrokeParameters? = null,
        @DrawableRes icon: Int? = R.drawable.ic_mood_peaceful
    ) = CircleParameters(
        radius,
        backgroundColor,
        stroke,
        icon
    )
}