package ge.sopovardidze.echojournal.presentation.entries

import androidx.lifecycle.ViewModel
import ge.sopovardidze.echojournal.presentation.entries.model.EntriesUiState
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

            EntriesListAction.OnMoodChipClick -> TODO()
            EntriesListAction.OnMoodClear -> TODO()
            is EntriesListAction.OnMoodSelectionChange -> TODO()
            EntriesListAction.OnTopicsChipClick -> TODO()
            EntriesListAction.OnTopicsClear -> TODO()
            is EntriesListAction.OnTopicsSelectionChange -> TODO()
        }
    }

    fun changeIsEmpty() {
        state.update {
            it.copy(
                pageIsEmpty = !state.value.pageIsEmpty
            )
        }
    }
}