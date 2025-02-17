package ge.sopovardidze.echojournal.core

import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
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

fun String.toMood(): FilterType.Mood {
    return when (this) {
        "Excited" -> FilterType.Mood(
            title = "Excited",
            icon = R.drawable.ic_mood_excited,
            outlinedIconRes = R.drawable.ic_mood_excited_outlined,
            isSelected = false,
            gradientStartColor = ExcitedStart,
            gradientEndColor = ExcitedEnd
        )

        "Peaceful" -> FilterType.Mood(
            title = "Peaceful",
            icon = R.drawable.ic_mood_peaceful,
            outlinedIconRes = R.drawable.ic_mood_peaceful_outlined,
            isSelected = false,
            gradientStartColor = PeacefulStart,
            gradientEndColor = PeacefulEnd
        )

        "Neutral" -> FilterType.Mood(
            title = "Neutral",
            icon = R.drawable.ic_mood_neutral,
            outlinedIconRes = R.drawable.ic_mood_neutral_outlined,
            isSelected = false,
            gradientStartColor = NeutralStart,
            gradientEndColor = NeutralEnd
        )

        "Sad" -> FilterType.Mood(
            title = "Sad",
            icon = R.drawable.ic_mood_sad,
            outlinedIconRes = R.drawable.ic_mood_sad_outlined,
            isSelected = false,
            gradientStartColor = SadStart,
            gradientEndColor = SadEnd
        )

        else -> FilterType.Mood(
            title = "Stressed",
            icon = R.drawable.ic_mood_engry,
            outlinedIconRes = R.drawable.ic_mood_stressed_outlined,
            isSelected = false,
            gradientStartColor = AngryStart,
            gradientEndColor = AngryEnd
        )
    }
}

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}