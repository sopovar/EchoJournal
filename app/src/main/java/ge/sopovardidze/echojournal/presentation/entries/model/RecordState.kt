package ge.sopovardidze.echojournal.presentation.entries.model

sealed class RecordState {
    object Idle: RecordState()
    object Paused: RecordState()
    object Recording: RecordState()
}