package ge.sopovardidze.echojournal.presentation.create_record

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

class CreateRecordViewModel: ViewModel() {

    var state = MutableStateFlow(CreateRecordState())
        private set

    fun setAudio(audio: String) {
        state.update {
            it.copy(
                audioRecord = audio
            )
        }
    }
}