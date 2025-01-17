package ge.sopovardidze.echojournal.presentation.records.model

data class MoodModel(
    val mood: String,
    val iconRes: Int,
    var isSelected: Boolean
)
