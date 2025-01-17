package ge.sopovardidze.echojournal.presentation.records

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.Constants.TOP_PADDING
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.records.components.FilterItem
import ge.sopovardidze.echojournal.presentation.records.components.MoodChip
import ge.sopovardidze.echojournal.presentation.records.components.RecordingBottomSheet
import ge.sopovardidze.echojournal.presentation.records.components.TopicsChip
import ge.sopovardidze.echojournal.presentation.records.components.comp_playground.RecordsList
import ge.sopovardidze.echojournal.presentation.records.model.RecordsUiState
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import ge.sopovardidze.echojournal.presentation.records.model.RecordState
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Shadow

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(
    modifier: Modifier = Modifier,
    state: RecordsUiState,
    onAction: (RecordListAction) -> Unit,
) {
    var chipsHeight by remember { mutableStateOf(0) }
    var filterBoxBounds by remember { mutableStateOf<androidx.compose.ui.geometry.Rect?>(null) }

    var showBottomSheet by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
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
//                    onAction.invoke(EntriesListAction.OnFabClick)
                    showBottomSheet = true
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(filterBoxBounds) {
                    detectTapGestures { offset ->
                        filterBoxBounds?.let {
                            if (!it.contains(offset)) {
                                Log.e("123123", "OUTSIDE CLICK ")
                                onAction.invoke(RecordListAction.OnOutsideBoundsClick)
                            }
                        }
                    }
                }
        ) {

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
                                        onAction.invoke(RecordListAction.OnMoodChipClick)
                                    },
                                    onClearAll = {
                                        onAction.invoke(RecordListAction.OnMoodClear)
                                    },
                                )

                                TopicsChip(
                                    selectedTopics = state.topicsList.filter { it.isSelected },
                                    isChipActive = state.isTopicsChipActive,
                                    onTopicClick = {
                                        onAction.invoke(RecordListAction.OnTopicsChipClick)
                                    },
                                    onClearAll = {
                                        onAction.invoke(RecordListAction.OnTopicsClear)
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
                                .padding(
                                    top = with(LocalDensity.current) { chipsHeight.toDp() },
                                    start = 16.dp,
                                    end = 16.dp
                                )
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
                                .clip(shape = RoundedCornerShape(10.dp))
                                .onGloballyPositioned { layoutCoordinates ->
                                    filterBoxBounds =
                                        layoutCoordinates.boundsInRoot()
                                },
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
                                                    RecordListAction.OnMoodSelectionChange(
                                                        filter
                                                    )
                                                )
                                            } else if (filter is FilterType.Topics) {
                                                onAction.invoke(
                                                    RecordListAction.OnTopicsSelectionChange(
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

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.wrapContentHeight(),
                sheetState = sheetState,
                tonalElevation = 12.dp,
                onDismissRequest = { showBottomSheet = false },
                scrimColor = Color.Transparent,
                containerColor = White
            ) {
                RecordingBottomSheet(
                    modifier = Modifier.wrapContentHeight(),
                    recordState = RecordState.Idle
                )
            }
        }
    }
}


@Preview
@Composable
private fun EntriesListScreenPreview() {
    EchoJournalTheme {
        RecordsScreen(
            state = RecordsUiState(),
            onAction = {

            }
        )
    }
}