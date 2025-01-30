package ge.sopovardidze.echojournal.presentation.create_record

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sopovardidze.echojournal.domain.usecases.InsertRecordUseCase
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateRecordViewModel @Inject constructor(
    private val createRecordUseCase: InsertRecordUseCase
) : ViewModel() {

    var state = MutableStateFlow(CreateRecordState())
        private set

    fun setAudio(audio: String) {
        state.update {
            it.copy(
                audioRecord = audio
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