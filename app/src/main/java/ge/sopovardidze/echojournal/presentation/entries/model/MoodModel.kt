package ge.sopovardidze.echojournal.presentation.entries.model

data class MoodModel(
    val mood: String,
    val iconRes: Int,
    var isSelected: Boolean
)
