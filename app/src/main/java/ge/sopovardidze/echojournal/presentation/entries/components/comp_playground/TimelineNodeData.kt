package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.compose.ui.graphics.Color

data class TimelineNodeData(
    val circleParameters: CircleParameters,
    val lineParameters: LineParameters?,
    val containerColor: Color
)