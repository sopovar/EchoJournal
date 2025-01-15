package ge.sopovardidze.echojournal.presentation.entries

import androidx.lifecycle.ViewModel
import ge.sopovardidze.echojournal.presentation.entries.model.EntriesUiState
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class EntriesListViewModel: ViewModel() {

    var state = MutableStateFlow(EntriesUiState())
        private set

    fun onAction(action: EntriesListAction) {
        when(action) {
            is EntriesListAction.OnFabClick -> {
                changeIsEmpty()
            }
            EntriesListAction.OnMoodChipClick -> {
                onMoodChipClick()
            }
            EntriesListAction.OnMoodClear -> {
                clearMoods()
            }
            is EntriesListAction.OnMoodSelectionChange -> {
                updateMood(action.mood)
            }
            EntriesListAction.OnTopicsChipClick -> {
                onTopicsChipClick()
            }
            EntriesListAction.OnTopicsClear -> {
                clearTopics()
            }
            is EntriesListAction.OnTopicsSelectionChange -> {
                updateTopic(action.mood)
            }

            EntriesListAction.OnOutsideBoundsClick -> {
                close()
            }
        }
    }

    private fun close() {
        state.update {
            it.copy(
                isTopicsChipActive = false,
                isMoodChipActive = false
            )
        }
    }

    private fun updateTopic(mood: FilterType.Topics) {
        state.update {
            it.copy(
                topicsList = it.topicsList.map { existingTopic ->
                    if (existingTopic.title == mood.title) {
                        existingTopic.copy(isSelected = !mood.isSelected)
                    } else {
                        existingTopic
                    }
                }
            )
        }
    }

    private fun updateMood(mood: FilterType.Mood) {
        state.update {
            it.copy(
                moodList = it.moodList.map { existingMood ->
                    if (existingMood.title == mood.title) {
                        existingMood.copy(isSelected = !mood.isSelected)
                    } else {
                        existingMood
                    }
                }
            )
        }
    }

    private fun clearTopics() {
        state.update {
            it.copy(
                isTopicsChipActive = false,
                topicsList = state.value.topicsList.map { mood ->
                    mood.copy(isSelected = false)
                }
            )
        }
    }

    private fun clearMoods() {
        state.update {
            it.copy(
                isMoodChipActive = false,
                moodList = state.value.moodList.map { mood ->
                    mood.copy(isSelected = false)
                }
            )
        }
    }

    private fun onTopicsChipClick() {
        state.update {
            it.copy(
                isTopicsChipActive = !state.value.isTopicsChipActive,
                isMoodChipActive = false
            )
        }
    }

    private fun onMoodChipClick() {
        state.update {
            it.copy(
                isMoodChipActive = !state.value.isMoodChipActive,
                isTopicsChipActive = false
            )
        }
    }

    private fun changeIsEmpty() {
        state.update {
            it.copy(
                pageIsEmpty = !state.value.pageIsEmpty
            )
        }
    }
}