package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.entries.model.MoodModel
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Secondary10

@Composable
fun MoodChip(
    modifier: Modifier = Modifier,
    selectedMoods: List<MoodModel>,
    isSelected: Boolean = true,
    onMoodClick: () -> Unit,
    onClearAll: () -> Unit,
) {
    GeneralChip(
        modifier = modifier,
        title = "All Moods",
        isSelected = isSelected,
        isEmpty = selectedMoods.isEmpty(),
        onChipClick = onMoodClick,
        onClearAll = onClearAll,
    ) {
        val selectedMoodTitles = selectedMoods.map { it.mood }.joinToString(", ")
        selectedMoods.forEachIndexed { index, mood ->
            Image(
                painter = painterResource(mood.iconRes),
                contentDescription = mood.mood,
                modifier = Modifier
                    .size(22.dp)
                    .offset(
                        x = if (index == 0) 0.dp else (-2).dp * index
                    ),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = selectedMoodTitles,
            color = Secondary10
        )
    }
}

@Preview
@Composable
private fun MoodChipPreview() {
    EchoJournalTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                ) {
                val mockMoodList = listOf(
                    MoodModel(
                        mood = "Neutral",
                        iconRes = R.drawable.ic_mood_neutral,
                        isSelected = true
                    ),
                    MoodModel(
                        mood = "Sad",
                        iconRes = R.drawable.ic_mood_sad,
                        isSelected = true
                    ),
                )
                MoodChip(
                    selectedMoods = mockMoodList,
                    isSelected = false,
                    onMoodClick = {},
                    onClearAll = {},
                )
            }
        }
    }
}

