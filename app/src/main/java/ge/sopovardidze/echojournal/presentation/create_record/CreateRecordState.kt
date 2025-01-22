package ge.sopovardidze.echojournal.presentation.create_record

import ge.sopovardidze.echojournal.presentation.records.model.FilterType

data class CreateRecordState(
    val title: String? = null,
    val selectedMood: FilterType.Mood? = null,
    val audioRecord: String? = null,
    val audioDuration: Int = 0,
    val topics: MutableSet<String> = emptySet<String>().toMutableSet(),
    val description: String? = null,
    val isPlaying: Boolean = false
)
