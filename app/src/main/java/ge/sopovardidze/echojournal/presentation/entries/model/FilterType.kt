package ge.sopovardidze.echojournal.presentation.entries.model

sealed class FilterType(
    val name: String,
    val iconRes: Int,
    val seleted: Boolean,
) {
    data class Mood(val title: String, val icon: Int, val isSelected: Boolean) :
        FilterType(title, icon, isSelected)

    data class Topics(val title: String, val icon: Int, val isSelected: Boolean) :
        FilterType(title, icon, isSelected)
}