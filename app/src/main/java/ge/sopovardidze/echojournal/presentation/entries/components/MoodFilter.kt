package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.entries.model.MoodModel
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Shadow
import ge.sopovardidze.echojournal.ui.theme.SurfaceTint5

@Composable
fun MoodFilter(
    modifier: Modifier = Modifier,
    selectedMoods: List<MoodModel>,
    allMoods: List<MoodModel>,
    onMoodSelected: (MoodModel) -> Unit,
    onClearAll: () -> Unit,
) {
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MoodChip(
            selectedMoods = selectedMoods,
            isSelected = expanded,
            onMoodClick = {

            },
            onClearAll = {
                onClearAll.invoke()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(10.dp),
                    color = Shadow.copy(0.2f),
                    blur = 20.dp,
                    offsetY = 6.dp
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        ) {
           Column(
               modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(2.dp)
           ) {
               allMoods.forEach { curMood ->
                   MoodFilterItem(
                       mood = curMood,
                       onSelected = {
                           onMoodSelected.invoke(curMood)
                       }
                   )
               }
           }
        }
    }
}

@Preview
@Composable
private fun MoodFilterPreview() {
    val mockMoodList = listOf(
        MoodModel(
            mood = "Excited",
            iconRes = R.drawable.ic_mood_excited,
            isSelected = true
        ),
        MoodModel(
            mood = "Peaceful",
            iconRes = R.drawable.ic_mood_peaceful,
            isSelected = false
        ),
        MoodModel(
            mood = "Neutral",
            iconRes = R.drawable.ic_mood_neutral,
            isSelected = false
        ),
        MoodModel(
            mood = "Sad",
            iconRes = R.drawable.ic_mood_sad,
            isSelected = true
        ),
        MoodModel(
            mood = "Stressed",
            iconRes = R.drawable.ic_mood_engry,
            isSelected = true
        ),
    )
    EchoJournalTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(24.dp)
            ) {
                MoodFilter(
                    selectedMoods = emptyList(),
                    allMoods = mockMoodList,
                    onMoodSelected = {

                    },
                    onClearAll = {

                    }
                )
            }
        }
    }
}