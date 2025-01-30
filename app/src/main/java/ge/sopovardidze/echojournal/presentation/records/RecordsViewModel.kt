package ge.sopovardidze.echojournal.presentation.records

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.echojournal.domain.usecases.GetAllRecordsUseCase
import ge.sopovardidze.echojournal.presentation.records.model.RecordsUiState
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val getAllRecordsUseCase: GetAllRecordsUseCase
): ViewModel() {

    var state = MutableStateFlow(RecordsUiState())
        private set

    fun onAction(action: RecordListAction) {
        when(action) {
            is RecordListAction.OnFabClick -> {
                changeIsEmpty()
            }
            RecordListAction.OnMoodChipClick -> {
                onMoodChipClick()
            }
            RecordListAction.OnMoodClear -> {
                clearMoods()
            }
            is RecordListAction.OnMoodSelectionChange -> {
                updateMood(action.mood)
            }
            RecordListAction.OnTopicsChipClick -> {
                onTopicsChipClick()
            }
            RecordListAction.OnTopicsClear -> {
                clearTopics()
            }
            is RecordListAction.OnTopicsSelectionChange -> {
                updateTopic(action.mood)
            }

            RecordListAction.OnOutsideBoundsClick -> {
                close()
            }

            is RecordListAction.OnStartNewRecord -> {

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