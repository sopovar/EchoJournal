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
    fun fromTopics(topics: List<String>): String {
        return topics.joinToString(",") { it }
    }

    @TypeConverter
    fun toTopics(topicsString: String): List<String> {
        return topicsString.split(",").map {  it }
    }
}