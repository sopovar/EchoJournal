package ge.sopovardidze.echojournal.presentation.records

import ge.sopovardidze.echojournal.presentation.records.model.FilterType

sealed interface RecordListAction {
    object OnMoodChipClick: RecordListAction
    object OnTopicsChipClick: RecordListAction
    data class OnMoodSelectionChange(val mood: FilterType.Mood): RecordListAction
    data class OnTopicsSelectionChange(val mood: FilterType.Topics): RecordListAction
    object OnMoodClear: RecordListAction
    object OnTopicsClear: RecordListAction
    object OnFabClick: RecordListAction
    object OnOutsideBoundsClick: RecordListAction
    data class OnStartNewRecord(val filePath: String): RecordListAction
}