package ge.sopovardidze.echojournal.presentation.create_record.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.core.Constants.mockMoodList
import ge.sopovardidze.echojournal.core.noRippleClickable
import ge.sopovardidze.echojournal.presentation.records.model.FilterType
import ge.sopovardidze.echojournal.ui.theme.BtnBgColor
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.LightBgBlue
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant50

@Composable
fun MoodSelectionBottomSheet(
    modifier: Modifier = Modifier,
    onMoodSelection: (FilterType.Mood) -> Unit,
    onCancel: () -> Unit,
) {

    val moods = remember {
        mutableStateOf(mockMoodList.map { it.copy(isSelected = false) })
    }

    val isConfirmEnabled = remember {
        derivedStateOf { moods.value.any { it.isSelected } }
    }

    Column(
        modifier = modifier
            .padding(top = 18.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "How are you doing?",
            style = MaterialTheme.typography.headlineMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            moods.value.forEach {
                val iconRes = if (it.isSelected) it.icon else it.outlinedIconRes
                val textColor = if (it.isSelected) Color.Black else NeutralVariant50
                Column(
                    modifier = Modifier
                        .noRippleClickable {
                            moods.value = moods.value.map { mood ->
                                if (mood.title == it.title) {
                                    mood.copy(isSelected = true)
                                } else {
                                    mood.copy(isSelected = false)
                                }
                            }
//                            onMoodSelection.invoke(it)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(iconRes),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.bodySmall.copy(color = textColor)
                    )
                }
            }
        }

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
                modifier = Modifier
                    .weight(1f)
                    .noRippleClickable {
                        onCancel.invoke()
                    },
                text = "Cancel",
                gradientStart = LightBgBlue,
                gradientEnd = LightBgBlue,
            )

            EchoButton(
                modifier = Modifier
                    .weight(3f)
                    .noRippleClickable {
                        moods.value.find { it.isSelected }?.let { onMoodSelection.invoke(it) }
                    },
                text = "Confirm",
                textColor = if (isConfirmEnabled.value) Color.White else NeutralVariant50,
                isEnabled = isConfirmEnabled.value,
                bgColor = BtnBgColor
            )
        }
    }
}

@Preview
@Composable
private fun MoodSelectionBottomSheetPreview() {
    EchoJournalTheme {
        Surface {
            MoodSelectionBottomSheet(
                onMoodSelection = {},
                onCancel = {}
            )
        }
    }
}