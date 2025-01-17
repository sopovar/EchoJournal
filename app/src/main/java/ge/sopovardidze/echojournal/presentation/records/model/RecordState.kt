package ge.sopovardidze.echojournal.presentation.records.model

sealed class RecordState {
    object Idle: RecordState()
    object Paused: RecordState()
    object Recording: RecordState()
}