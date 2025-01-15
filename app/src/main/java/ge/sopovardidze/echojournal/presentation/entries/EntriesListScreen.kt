package ge.sopovardidze.echojournal.presentation.entries

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.Constants.TOP_PADDING
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.entries.components.FilterItem
import ge.sopovardidze.echojournal.presentation.entries.components.MoodChip
import ge.sopovardidze.echojournal.presentation.entries.components.TopicsChip
import ge.sopovardidze.echojournal.presentation.entries.components.comp_playground.RecordsList
import ge.sopovardidze.echojournal.presentation.entries.model.EntriesUiState
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Shadow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EntriesListScreen(
    modifier: Modifier = Modifier,
    state: EntriesUiState,
    onAction: (EntriesListAction) -> Unit,
) {
    var chipsHeight by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your EchoJournal",
                    style = MaterialTheme.typography.headlineLarge
                )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                    contentDescription = "Settings"
                )
            }
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    onAction.invoke(EntriesListAction.OnFabClick)
                },
                shape = CircleShape,
                containerColor = Color.Transparent,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_fab_add),
                    contentDescription = "FabAdd",
                )
            }
        }
    ) { padding ->
        if (state.pageIsEmpty) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_empty),
                    contentDescription = "Empty"
                )
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "No Entries",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Start recording your first Echo ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onSizeChanged { size ->
                                chipsHeight = size.height + TOP_PADDING // More elegant way?
                            }
                    ) {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            MoodChip(
                                moodList = state.moodList.filter { it.isSelected },
                                isSelected = state.isMoodChipActive,
                                onMoodChipClick = {
                                    onAction.invoke(EntriesListAction.OnMoodChipClick)
                                },
                                onClearAll = {
                                    onAction.invoke(EntriesListAction.OnMoodClear)
                                },
                            )

                            TopicsChip(
                                selectedTopics = state.topicsList.filter { it.isSelected },
                                isChipActive = state.isTopicsChipActive,
                                onTopicClick = {
                                    onAction.invoke(EntriesListAction.OnTopicsChipClick)
                                },
                                onClearAll = {
                                    onAction.invoke(EntriesListAction.OnTopicsClear)
                                },
                            )
                        }
                    }
                    RecordsList()
                }
                val listToShow = if (state.isMoodChipActive) {
                    state.moodList
                } else if (state.isTopicsChipActive) {
                    state.topicsList
                } else {
                    emptyList()
                }
                if (listToShow.isNotEmpty()) {
                    Log.e("123123", "chip height = ${chipsHeight}")
                    Box(
                        modifier = Modifier
                            .padding(top = with(LocalDensity.current) { chipsHeight.toDp() })
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
                                            onAction.invoke(
                                                EntriesListAction.OnMoodSelectionChange(
                                                    filter
                                                )
                                            )
                                        } else if (filter is FilterType.Topics) {
                                            onAction.invoke(
                                                EntriesListAction.OnTopicsSelectionChange(
                                                    filter
                                                )
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun EntriesListScreenPreview() {
    EchoJournalTheme {
        EntriesListScreen(
            state = EntriesUiState(),
            onAction = {

            }
        )
    }
}