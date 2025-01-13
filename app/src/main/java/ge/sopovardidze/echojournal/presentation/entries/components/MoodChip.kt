package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.entries.model.MoodModel
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Secondary10
import ge.sopovardidze.echojournal.ui.theme.Secondary50

@Composable
fun MoodChip(
    modifier: Modifier = Modifier,
    selectedMoods: List<MoodModel>,
    isSelected: Boolean = true,
    onMoodClick: () -> Unit,
    onClearAll: () -> Unit,
) {

    val shape = CircleShape
    val borderStrokeColor = if (isSelected) {
        Secondary50
    } else if (selectedMoods.isNotEmpty()) {
        Transparent
    } else {
        LightGray
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                vertical = 2.dp,
                horizontal = 4.dp
            )
            .then(
                if (!isSelected && selectedMoods.isEmpty()) {
                    Modifier
                } else {
                    Modifier.dropShadow(
                        shape = shape,
                        blur = 12.dp,
                        offsetY = 4.dp
                    )
                }
            )
            .border(
                width = 1.dp,
                color = borderStrokeColor,
                shape = shape
            )
            .background(
                color = if (isSelected || selectedMoods.isNotEmpty()) White else MaterialTheme.colorScheme.inverseOnSurface,
                shape = shape
            )
            .clip(shape = shape)
            .clickable {
                onMoodClick.invoke()
            }
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        if (selectedMoods.isEmpty()) {
            Text(
                text = "All Moods",
                color = Secondary10
            )
        } else {
            val selectedMoodTitles = selectedMoods.map {
                it.mood
            }.joinToString(", ")
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
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.ic_trailing_icon),
                contentDescription = "clearAll",
                modifier = Modifier.clickable {
                    onClearAll.invoke()
                }
            )
        }
    }
}

@Preview
@Composable
private fun MoodChipPreview() {
    EchoJournalTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),

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

