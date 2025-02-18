package ge.sopovardidze.echojournal.presentation.create_record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.echojournal.core.Converters
import ge.sopovardidze.echojournal.data.local.entity.RecordEntity
import ge.sopovardidze.echojournal.domain.usecases.InsertRecordUseCase
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateRecordViewModel @Inject constructor(
    private val createRecordUseCase: InsertRecordUseCase
) : ViewModel() {

    var state = MutableStateFlow(CreateRecordState())
        private set

    private val _recordInserted = MutableStateFlow<Boolean?>(null)
    val recordInserted: StateFlow<Boolean?> = _recordInserted.asStateFlow()

    fun createRecord() {
        val record = RecordEntity(
            id = UUID.randomUUID().toString(),
            title = state.value.title ?: "",
            mood = state.value.selectedMood?.title ?: "",
            date = System.currentTimeMillis(),
            description = state.value.description ?: "",
            topics = Converters().fromTopics(state.value.topics.toList())
        )
        viewModelScope.launch {
            createRecordUseCase.invoke(record.toRecordModel()).collect { success ->
                if (success) {
                    _recordInserted.value = true
                } else {
                    resetInsertionState()
                }
            }
        }
    }

    fun resetInsertionState() {
        _recordInserted.value = null
    }

    fun setAudioAndTime(audio: String, time: String?) {
        state.update {
            it.copy(
                audioRecord = audio,
                time = time
            )
        }
    }

    fun setMood(mood: FilterType.Mood) {
        state.update {
            it.copy(
                selectedMood = mood
            )
        }
    }

    fun setTitle(title: String) {
        state.update {
            it.copy(
                title = title
            )
        }
    }

    fun setDescription(desc: String) {
        state.update {
            it.copy(
                description = desc
            )
        }
    }

    fun setTopics(topicSet: Set<String>) {
        state.update {
            it.copy(
                topics = topicSet.toMutableSet()
            )
        }
    }
}