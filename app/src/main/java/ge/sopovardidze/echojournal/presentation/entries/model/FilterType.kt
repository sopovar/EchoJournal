package ge.sopovardidze.echojournal.presentation.entries.model

import androidx.compose.ui.graphics.Color
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.ui.theme.PeacefulEnd
import ge.sopovardidze.echojournal.ui.theme.PeacefulStart

sealed class FilterType(
    val name: String,
    val iconRes: Int,
    val selected: Boolean,
) {
    data class Mood(
        val title: String = "",
        val icon: Int,
        val isSelected: Boolean = false,
        val gradientStartColor: Color = PeacefulStart,
        val gradientEndColor: Color = PeacefulEnd,
    ) :
        FilterType(title, icon, isSelected)

    data class Topics(
        val title: String,
        val icon: Int = R.drawable.ic_tag,
        val isSelected: Boolean = false,
    ) :
        FilterType(title, icon, isSelected)
}