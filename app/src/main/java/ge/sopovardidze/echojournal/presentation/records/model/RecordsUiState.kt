package ge.sopovardidze.echojournal.presentation.records.model

import ge.sopovardidze.echojournal.core.Constants.mockMoodList
import ge.sopovardidze.echojournal.core.Constants.mockTopics

data class RecordsUiState(
    val moodList: List<FilterType.Mood> = mockMoodList,
    val topicsList: List<FilterType.Topics> = mockTopics,
    val isMoodChipActive: Boolean = false,
    val isTopicsChipActive: Boolean = false,
    val pageIsEmpty: Boolean = false,
    val records: List<RecordModel> = emptyList()
)
