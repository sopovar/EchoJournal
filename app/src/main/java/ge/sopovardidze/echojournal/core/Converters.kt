package ge.sopovardidze.echojournal.core

import androidx.room.TypeConverter
import ge.sopovardidze.echojournal.presentation.records.model.FilterType

class Converters {

    @TypeConverter
    fun fromMood(mood: FilterType.Mood): String {
        return mood.title
    }

    @TypeConverter
    fun toMood(title: String): FilterType.Mood {
        return title.toMood()
    }

    @TypeConverter
    fun fromTopics(topics: List<FilterType.Topics>): String {
        return topics.joinToString(",") { it.title }
    }

    @TypeConverter
    fun toTopics(topicsString: String): List<FilterType.Topics> {
        return topicsString.split(",").map { FilterType.Topics(title = it) }
    }
}