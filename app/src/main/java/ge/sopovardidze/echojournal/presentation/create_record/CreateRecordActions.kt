package ge.sopovardidze.echojournal.presentation.create_record

import ge.sopovardidze.echojournal.presentation.records.model.FilterType

sealed interface CreateRecordActions {
    data class NewMoodSelected(val mood: FilterType.Mood): CreateRecordActions
    data class SetTitle(val title: String): CreateRecordActions
    data class SetDescription(val description: String): CreateRecordActions
    data class SetSelectedTopics(val topics: Set<String>): CreateRecordActions
}