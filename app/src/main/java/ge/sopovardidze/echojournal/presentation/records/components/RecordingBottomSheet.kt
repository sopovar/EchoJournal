package ge.sopovardidze.echojournal.presentation.records.components

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.noRippleClickable
import ge.sopovardidze.echojournal.presentation.records.model.RecordState
import ge.sopovardidze.echojournal.presentation.records.recorder.EchoAudioRecorder
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30
import java.io.File

@Composable
fun RecordingBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: (String?) -> Unit,
) {
    val context = LocalContext.current
    var isRecording by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var recordedFile by remember { mutableStateOf<File?>(null) }

    var hasPermission by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasPermission = isGranted
        }
    )

    val title = if (isRecording) {
        "Recording your memories..."
    } else if (isPaused) {
        "Resume Recording"
    } else {
        "Start Recording"
    }

    val middleBtn = if (isRecording) {
        R.drawable.ic_recording
    } else {
        R.drawable.ic_record
    }

    val rightBtn = if (isPaused) {
        R.drawable.ic_finish_record
    } else {
        R.drawable.ic_pause_record
    }

    LaunchedEffect(key1 = Unit) {
        hasPermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        if (!hasPermission) {
            launcher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    val recorder by lazy {
        EchoAudioRecorder(context)
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

        if (!hasPermission) {
            Text(
                text = "For recording audio we need your permission!"
            )
        } else {
            Row(
                modifier = Modifier
                    .padding(top = 42.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_cancel_recod),
                    contentDescription = "cancelRecord",
                    modifier = Modifier.clickable {
                        Log.e("123123", "on cancel record click")
                        recorder.stop()
                        onDismiss.invoke(recordedFile?.absolutePath)
                    }
                )
                Image(
                    imageVector = ImageVector.vectorResource(middleBtn),
                    contentDescription = "record",
                    modifier = Modifier.noRippleClickable {
                        if (!isRecording) {
                            Log.e("123123", "on start record click")
                            File(context.cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                recordedFile = it
                            }
                            isRecording = true
                            isPaused = false
                        } else {
                            Log.e("123123", "on stop record click")
                            recorder.stop()
                            isRecording = false
                            isPaused = false
                            if (recordedFile != null) {
                                Log.e("123123", "absolutePath = ${recordedFile?.absolutePath}")
                            } else {
                                Log.e("123123", "We failed recording file")
                            }
                        }
                    }
                )
                Image(
                    imageVector = ImageVector.vectorResource(rightBtn),
                    contentDescription = "rightBtn",
                    modifier = Modifier.clickable {
                        Log.e("123123", "on PAUSE")
                        if (isRecording && isPaused) {
                            recorder.resume()
                            isPaused = false
                            isRecording = true
                        } else if (isRecording) {
                            recorder.pause()
                            isRecording = false
                            isPaused = true
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecordingBottomSheetPreview() {
    EchoJournalTheme {
        Surface {
            RecordingBottomSheet {}
        }
    }
}