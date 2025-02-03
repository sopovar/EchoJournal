package ge.sopovardidze.echojournal.presentation.create_record

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.noRippleClickable
import ge.sopovardidze.echojournal.presentation.create_record.component.EchoButton
import ge.sopovardidze.echojournal.presentation.create_record.component.MoodSelectionBottomSheet
import ge.sopovardidze.echojournal.presentation.create_record.component.TopicTagsCreator
import ge.sopovardidze.echojournal.presentation.navigation.CreateRecord
import ge.sopovardidze.echojournal.presentation.records.components.Audio
import ge.sopovardidze.echojournal.ui.theme.BtnBgColor
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.LightBgBlue
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant50
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecordScreen(
    modifier: Modifier = Modifier,
    createRecord: CreateRecord,
    state: CreateRecordState,
    onAction: (CreateRecordActions) -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        WindowInsets.statusBars.asPaddingValues()
                    )
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp),
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "back"
                )
                Text(
                    text = "New entry",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        WindowInsets.navigationBars.asPaddingValues()
                    )
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                EchoButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    gradientStart = LightBgBlue,
                    gradientEnd = LightBgBlue,
                )

                EchoButton(
                    modifier = Modifier.weight(3f).noRippleClickable {
                        onAction.invoke(CreateRecordActions.InsertRecord)
                    },
                    text = "Save",
                    textColor = if (state.isBtnEnabled()) Color.White else NeutralVariant50,
                    isEnabled = state.isBtnEnabled(),
                    bgColor = BtnBgColor,
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val iconRes =
                    if (state.selectedMood != null) state.selectedMood.iconRes else R.drawable.ic_add_mood
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = "newMood",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            showBottomSheet = true
                        }
                )
                TextField(
                    value = state.title ?: "",
                    onValueChange = {
                        onAction.invoke(CreateRecordActions.SetTitle(it))
                    },
                    placeholder = {
                        Text(
                            text = "Add title...",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = NeutralVariant80
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )
            }

            Audio(
                recordedFilePath = state.audioRecord,
                timeProgress = "0:00/1:23",
                height = 40.dp,
            )

            TopicTagsCreator(
                topics = state.topics,
                onSelectionChange = {
                    onAction.invoke(CreateRecordActions.SetSelectedTopics(it))
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "description",
                    tint = NeutralVariant80
                )
                TextField(
                    value = state.description ?: "",
                    onValueChange = { desc ->
                        onAction.invoke(CreateRecordActions.SetDescription(desc))
                    },
                    placeholder = {
                        Text(
                            text = "Description...",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = NeutralVariant80
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )
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
                MoodSelectionBottomSheet(
                    modifier = Modifier.wrapContentHeight(),
                    onMoodSelection = {
                        onAction.invoke(CreateRecordActions.NewMoodSelected(it))
                        showBottomSheet = false
                    },
                    onCancel = {
                        showBottomSheet = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreateRecordScreenPreview() {
    EchoJournalTheme {
        Surface {
            CreateRecordScreen(
                modifier = Modifier
                    .fillMaxSize(),
                createRecord = CreateRecord("filePath"),
                state = CreateRecordState(),
                onAction = {}
            )
        }
    }
}