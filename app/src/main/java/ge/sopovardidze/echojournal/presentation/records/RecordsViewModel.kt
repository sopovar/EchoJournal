package ge.sopovardidze.echojournal.presentation.records

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.echojournal.domain.usecases.GetAllRecordsUseCase
import ge.sopovardidze.echojournal.presentation.records.model.RecordsUiState
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val getAllRecordsUseCase: GetAllRecordsUseCase
): ViewModel() {

    var state = MutableStateFlow(RecordsUiState())
        private set

    private var timerJob: Job? = null
    private var isRecording = false
    private var count = 0L

    private fun startTimer() {
        timerJob?.cancel()
        isRecording = true
        timerJob = viewModelScope.launch {
            while (isRecording) {
                delay(1000)
                count++
                state.update {
                    it.copy(
                        timer = count
                    )
                }
            }
        }
    }

    private fun pauseTimer() {
        isRecording = false
        timerJob?.cancel()
    }

    private fun stopTimer() {
        isRecording = false
        count = 0L
        state.update {
            it.copy(
                timer = 0L
            )
        }
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    fun getRecords() {
        viewModelScope.launch {
            getAllRecordsUseCase.invoke().collectLatest { cachedRecords ->
                state.update {
                    it.copy(
                        records = cachedRecords
                    )
                }
            }
        }
    }


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
            RecordListAction.StartTimer -> {
                startTimer()
            }
            RecordListAction.PauseTimer -> {
                pauseTimer()
            }
            RecordListAction.StopTimer -> {
                stopTimer()
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