package ge.sopovardidze.echojournal.presentation.entries

import ge.sopovardidze.echojournal.presentation.entries.model.FilterType

sealed interface EntriesListAction {
    object OnMoodChipClick: EntriesListAction
    object OnTopicsChipClick: EntriesListAction
    data class OnMoodSelectionChange(val mood: FilterType.Mood): EntriesListAction
    data class OnTopicsSelectionChange(val mood: FilterType.Topics): EntriesListAction
    object OnMoodClear: EntriesListAction
    object OnTopicsClear: EntriesListAction
    object OnFabClick: EntriesListAction
}