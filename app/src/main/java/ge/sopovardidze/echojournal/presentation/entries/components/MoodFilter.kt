package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.entries.EntriesListAction
import ge.sopovardidze.echojournal.presentation.entries.model.EntriesUiState
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Shadow
import kotlin.math.truncate


@Composable
fun MoodFilter(
    modifier: Modifier = Modifier,
    state: EntriesUiState,
    action: (EntriesListAction) -> Unit,
) {
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MoodChip(
                moodList = state.moodList.filter { it.isSelected },
                isSelected = state.isMoodChipActive,
                onMoodChipClick = {
                    action.invoke(EntriesListAction.OnMoodChipClick)
                },
                onClearAll = {
                    action.invoke(EntriesListAction.OnMoodClear)
                },
                modifier = Modifier.weight(1f)
            )

            TopicsChip(
                selectedTopics = state.topicsList.filter { it.isSelected },
                isChipActive = state.isTopicsChipActive,
                onTopicClick = {
                    action.invoke(EntriesListAction.OnTopicsChipClick)
                },
                onClearAll = {
                    action.invoke(EntriesListAction.OnTopicsClear)
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        val listToShow = if (state.isMoodChipActive) {
            state.moodList
        } else if (state.isTopicsChipActive) {
            state.topicsList
        } else {
            emptyList()
        }
        if (listToShow.isNotEmpty()) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    listToShow.forEach { curMood ->
                        FilterItem(
                            mood = curMood,
                            onSelectionChange = { filter ->
                                if (filter is FilterType.Mood) {
                                    action.invoke(EntriesListAction.OnMoodSelectionChange(filter))
                                } else if (filter is FilterType.Topics) {
                                    action.invoke(EntriesListAction.OnTopicsSelectionChange(filter))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MoodFilterPreview() {
    EchoJournalTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(24.dp)
            ) {
                MoodFilter(
                    state = EntriesUiState().copy(isMoodChipActive = true),
                    action = {}
                )
            }
        }
    }
}