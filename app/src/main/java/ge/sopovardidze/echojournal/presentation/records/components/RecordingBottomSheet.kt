package ge.sopovardidze.echojournal.presentation.records.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.records.model.RecordState
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30

@Composable
fun RecordingBottomSheet(
    modifier: Modifier = Modifier,
    recordState: RecordState,
) {

    val state by remember {
        mutableStateOf(recordState)
    }
    val title = when (state) {
        RecordState.Idle -> "Start Recording"
        RecordState.Paused -> "Recording paused"
        RecordState.Recording -> "Recording your memories..."
    }

    val middleBtn = when (state) {
        RecordState.Idle, RecordState.Paused -> R.drawable.ic_record
        RecordState.Recording -> R.drawable.ic_recording
    }

    val rightBtn = when (state) {
        RecordState.Idle, RecordState.Recording -> R.drawable.ic_pause_record
        RecordState.Paused -> R.drawable.ic_recording
    }

    Column(
        modifier = modifier
            .padding(top = 18.dp, bottom = 42.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "00:00:00",
            style = MaterialTheme.typography.bodySmall.copy(color = NeutralVariant30)
        )

        Row(
            modifier = Modifier
                .padding(top = 42.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_cancel_recod),
                contentDescription = "cancelRecord"
            )
            Image(
                imageVector = ImageVector.vectorResource(middleBtn),
                contentDescription = "record"
            )
            Image(
                imageVector = ImageVector.vectorResource(rightBtn),
                contentDescription = "rightBtn"
            )
        }
    }
}

@Preview
@Composable
private fun RecordingBottomSheetPreview() {
    EchoJournalTheme {
        Surface {
            RecordingBottomSheet(
                recordState = RecordState.Recording
            )
        }
    }
}