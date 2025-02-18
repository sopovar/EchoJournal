package ge.sopovardidze.echojournal.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data class CreateRecord(
    val filePath: String,
    val time: String?
)
