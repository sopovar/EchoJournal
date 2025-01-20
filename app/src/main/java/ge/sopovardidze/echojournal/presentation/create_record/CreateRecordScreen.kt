package ge.sopovardidze.echojournal.presentation.create_record

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.create_record.component.EchoButton
import ge.sopovardidze.echojournal.presentation.create_record.component.TopicTagsCreator
import ge.sopovardidze.echojournal.presentation.navigation.CreateRecord
import ge.sopovardidze.echojournal.presentation.records.components.Audio
import ge.sopovardidze.echojournal.ui.theme.BtnBgColor
import ge.sopovardidze.echojournal.ui.theme.BtnGradientStart
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.LightBgBlue
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant50
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant80

@Composable
fun CreateRecordScreen(
    modifier: Modifier = Modifier,
    createRecord: CreateRecord,
) {
    val saveButtonEnabled by remember {
        mutableStateOf(false)
    }
    var recordTitle by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        WindowInsets.statusBars.asPaddingValues()
                    )
                    .padding(24.dp),
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
                    modifier = Modifier.weight(3f),
                    text = "Save",
                    textColor = if (saveButtonEnabled) Color.White else NeutralVariant50,
                    isEnabled = saveButtonEnabled,
                    bgColor = BtnBgColor
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_add_mood),
                    contentDescription = "newMood"
                )
                TextField(
                    value = recordTitle,
                    onValueChange = {
                        recordTitle = it
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
                color = BtnGradientStart,
                timeProgress = "0:00/1:23",
                isPlaying = false,
                height = 40.dp
            )

            TopicTagsCreator(

            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var description by remember { mutableStateOf("") }
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "description",
                    tint = NeutralVariant80
                )
                TextField(
                    value = description,
                    onValueChange = {
                        description = it
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
                createRecord = CreateRecord("filePath")
            )
        }
    }
}