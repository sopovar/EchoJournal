package ge.sopovardidze.echojournal.core

import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType

object Constants {

    val mockMoodList = listOf(
        FilterType.Mood(
            title = "Excited",
            icon = R.drawable.ic_mood_excited,
            isSelected = true
        ),
        FilterType.Mood(
            title = "Peaceful",
            icon = R.drawable.ic_mood_peaceful,
            isSelected = false
        ),
        FilterType.Mood(
            title = "Neutral",
            icon = R.drawable.ic_mood_neutral,
            isSelected = false
        ),
        FilterType.Mood(
            title = "Sad",
            icon = R.drawable.ic_mood_sad,
            isSelected = false
        ),
        FilterType.Mood(
            title = "Stressed",
            icon = R.drawable.ic_mood_engry,
            isSelected = false
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
}