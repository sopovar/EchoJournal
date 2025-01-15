package ge.sopovardidze.echojournal.core

import androidx.compose.ui.graphics.Color
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType
import ge.sopovardidze.echojournal.presentation.entries.model.RecordModel
import ge.sopovardidze.echojournal.ui.theme.AngryEnd
import ge.sopovardidze.echojournal.ui.theme.AngryStart
import ge.sopovardidze.echojournal.ui.theme.ExcitedEnd
import ge.sopovardidze.echojournal.ui.theme.ExcitedStart
import ge.sopovardidze.echojournal.ui.theme.NeutralEnd
import ge.sopovardidze.echojournal.ui.theme.NeutralStart
import ge.sopovardidze.echojournal.ui.theme.PeacefulEnd
import ge.sopovardidze.echojournal.ui.theme.PeacefulStart
import ge.sopovardidze.echojournal.ui.theme.SadEnd
import ge.sopovardidze.echojournal.ui.theme.SadStart
import java.util.Calendar
import kotlin.random.Random

object Constants {

    const val TOP_PADDING = 200

    val mockMoodList = listOf(
        FilterType.Mood(
            title = "Excited",
            icon = R.drawable.ic_mood_excited,
            isSelected = true,
            gradientStartColor = ExcitedStart,
            gradientEndColor = ExcitedEnd
        ),
        FilterType.Mood(
            title = "Peaceful",
            icon = R.drawable.ic_mood_peaceful,
            isSelected = false,
            gradientStartColor = PeacefulStart,
            gradientEndColor = PeacefulEnd
        ),
        FilterType.Mood(
            title = "Neutral",
            icon = R.drawable.ic_mood_neutral,
            isSelected = false,
            gradientStartColor = NeutralStart,
            gradientEndColor = NeutralEnd
        ),
        FilterType.Mood(
            title = "Sad",
            icon = R.drawable.ic_mood_sad,
            isSelected = false,
            gradientStartColor = SadStart,
            gradientEndColor = SadEnd
        ),
        FilterType.Mood(
            title = "Stressed",
            icon = R.drawable.ic_mood_engry,
            isSelected = false,
            gradientStartColor = AngryStart,
            gradientEndColor = AngryEnd
        ),
    )

    val mockTopics = listOf(
        FilterType.Topics(
            title = "Work",
            icon = R.drawable.ic_tag,
            isSelected = false
        ),
        FilterType.Topics(
            title = "Friends",
            icon = R.drawable.ic_tag,
            isSelected = true
        ),
        FilterType.Topics(
            title = "Family",
            icon = R.drawable.ic_tag,
            isSelected = true
        ),
        FilterType.Topics(
            title = "Love",
            icon = R.drawable.ic_tag,
            isSelected = true
        ),
        FilterType.Topics(
            title = "Surprise",
            icon = R.drawable.ic_tag,
            isSelected = false
        ),
    )

    fun getMockRecords(): List<RecordModel> {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis

        val yesterday = Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
        }.timeInMillis

        val jan17 = Calendar.getInstance().apply {
            set(2025, Calendar.JANUARY, 17)
        }.timeInMillis

        val jan10 = Calendar.getInstance().apply {
            set(2025, Calendar.JANUARY, 10)
        }.timeInMillis
        return emptyList()
    }

    val mockDataList = listOf(
        RecordModel(
            id = "1",
            date = System.currentTimeMillis(),
            mood = mockMoodList[0],
            topics = mockTopics.subList(0,2),
            title = "Today’s Record",
            description = "This is the record for today."
        ),
        RecordModel(
            id = "2",
            date = System.currentTimeMillis() - (1000 * 60 * 60 * 24), // 24 hour ago
            mood = mockMoodList[1],
            topics = mockTopics.subList(1,2),
            title = "Yesterday’s Record 1",
            description = "This is the first record for yesterday. This is the first record for yesterday. This is the first record for yesterday. This is the first record for yesterday."
        ),
        RecordModel(
            id = "3",
            date = System.currentTimeMillis() - (1000 * 60 * 60 * 26), // 26 hours ago
            mood = mockMoodList[3],
            topics = mockTopics.subList(2,4),
            title = "Yesterday’s Record 2",
            description = "This is the second record for yesterday."
        ),
        RecordModel(
            id = "4",
            date = 1673919600000, // 17 January 2025, 12:00 AM
            mood = mockMoodList[4],
            topics = mockTopics.subList(0,1),
            title = "17 January Record 1",
            description = "This is a record for 17 January at noon."
        ),
        RecordModel(
            id = "5",
            date = 1673937600000, // 17 January 2025, 6:00 AM
            mood = mockMoodList[2],
            topics = mockTopics.subList(1,3),
            title = "17 January Record 2",
            description = "This is a record for 17 January at 6 AM."
        ),
        RecordModel(
            id = "6",
            date = 1673959200000, // 17 January 2025, 9:00 AM
            mood = mockMoodList[2],
            topics = mockTopics.subList(0,2),
            title = "17 January Record 3",
            description = "This is a record for 17 January at 9 AM."
        ),
        RecordModel(
            id = "7",
            date = 1673238000000, // 10 January 2025, 12:00 AM
            mood = mockMoodList[0],
            topics = mockTopics.subList(1,4),
            title = "10 January Record 1",
            description = "This is a record for 10 January at midnight."
        ),
        RecordModel(
            id = "8",
            date = 1673263200000, // 10 January 2025, 3:00 AM
            mood = mockMoodList[1],
            topics = mockTopics.subList(3,4),
            title = "10 January Record 2",
            description = "This is a record for 10 January at 3 AM."
        ),
        RecordModel(
            id = "9",
            date = 1673284800000, // 10 January 2025, 6:00 AM
            mood = mockMoodList[4],
            topics = mockTopics.subList(1,2),
            title = "10 January Record 3",
            description = "This is a record for 10 January at 6 AM."
        ),
        RecordModel(
            id = "10",
            date = 1673306400000, // 10 January 2025, 9:00 AM
            mood = mockMoodList[1],
            topics = mockTopics.subList(0,4),
            title = "10 January Record 4",
            description = "This is a record for 10 January at 9 AM."
        )
    )

    fun randomColor(): Color {
        val red = Random.nextFloat()
        val green = Random.nextFloat()
        val blue = Random.nextFloat()
        val alpha = 0.7f // Alpha value of 0.5

        return Color(red, green, blue, alpha)
    }

}