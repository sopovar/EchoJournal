package ge.sopovardidze.echojournal.presentation.records.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.records.RecordListAction
import ge.sopovardidze.echojournal.presentation.records.model.RecordsUiState
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Shadow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EchoFilter(
    modifier: Modifier = Modifier,
    state: RecordsUiState,
    action: (RecordListAction) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            MoodChip(
                moodList = state.moodList.filter { it.isSelected },
                isSelected = state.isMoodChipActive,
                onMoodChipClick = {
                    action.invoke(RecordListAction.OnMoodChipClick)
                },
                onClearAll = {
                    action.invoke(RecordListAction.OnMoodClear)
                },
            )

            TopicsChip(
                selectedTopics = state.topicsList.filter { it.isSelected },
                isChipActive = state.isTopicsChipActive,
                onTopicClick = {
                    action.invoke(RecordListAction.OnTopicsChipClick)
                },
                onClearAll = {
                    action.invoke(RecordListAction.OnTopicsClear)
                },
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
                                    action.invoke(RecordListAction.OnMoodSelectionChange(filter))
                                } else if (filter is FilterType.Topics) {
                                    action.invoke(RecordListAction.OnTopicsSelectionChange(filter))
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
                EchoFilter(
                    state = RecordsUiState().copy(isMoodChipActive = true),
                    action = {}
                )
            }
        }
    }
}
