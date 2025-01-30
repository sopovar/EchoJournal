package ge.sopovardidze.echojournal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ge.sopovardidze.echojournal.core.Converters
import ge.sopovardidze.echojournal.core.toMood
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import ge.sopovardidze.echojournal.presentation.records.model.RecordModel

@Entity(tableName = "RECORDS_TABLE")
data class RecordEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val mood: String,
    val date: Long,
    val description: String,
    val topics: String,
) {

    fun toRecordModel(): RecordModel {
        return RecordModel(
            id = id,
            title = title,
            mood = mood.toMood(),
            date = date,
            description = description,
            topics = Converters().toTopics(topics)
        )
    }
}
