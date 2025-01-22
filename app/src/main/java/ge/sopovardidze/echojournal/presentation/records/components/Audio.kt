package ge.sopovardidze.echojournal.presentation.records.components

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.ui.theme.BtnTextColor
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.LightBgBlue
import ge.sopovardidze.echojournal.ui.theme.Secondary80
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30
import ge.sopovardidze.echojournal.ui.theme.Shadow
import kotlinx.coroutines.delay
import java.io.File
import java.io.IOException

@Composable
fun Audio(
    modifier: Modifier = Modifier,
    recordedFilePath: String? = null,
    iconColor: Color = BtnTextColor,
    bgColor: Color = LightBgBlue,
    indicatorColor: Color = Secondary80,
    timeProgress: String,
    height: Dp = 44.dp,
) {
    val shape = CircleShape
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0f) }
    var duration by remember { mutableStateOf(1) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    val icon = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play

    LaunchedEffect(isPlaying) {
        mediaPlayer?.let {
            if (isPlaying) {
                while (it.isPlaying) {
                    currentPosition = it.currentPosition.toFloat()
                    delay(100)
                }
                if (!it.isPlaying) {
                    isPlaying = false
                    currentPosition = 0f
                }
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                color = bgColor,
                shape = shape
            )
            .padding(start = 4.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .dropShadow(
                    shape = shape,
                    color = Shadow.copy(0.08f),
                    blur = 12.dp,
                    offsetY = 4.dp
                )
                .background(
                    color = White,
                    shape = shape
                )
                .clip(shape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = "Play/Pause",
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier.clickable {
                    recordedFilePath?.let { path ->
                        val file = File(path)

                        if (!file.exists()) {
                            Log.e("Audio", "Audio file not found: $recordedFilePath")
                            return@clickable
                        }

                        if (mediaPlayer == null) {
                            mediaPlayer = MediaPlayer().apply {
                                try {
                                    setDataSource(file.absolutePath)
                                    prepare()
                                    duration = this.duration
                                } catch (e: IOException) {
                                    Log.e("Audio", "MediaPlayer initialization error: ${e.message}")
                                    return@clickable
                                }
                            }
                        }

                        mediaPlayer?.let {
                            if (it.isPlaying) {
                                it.pause()
                                isPlaying = false
                            } else {
                                it.start()
                                isPlaying = true
                            }
                        }
                    }
                },
            )
        }

        val progress = if (duration > 0) currentPosition / duration else 0f
        LinearProgressIndicator(
            progress = { progress },
            color = iconColor,
            trackColor = indicatorColor
        )

        Text(
            text = timeProgress,
            style = MaterialTheme.typography.bodySmall.copy(
                color = NeutralVariant30
            )
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
            isPlaying = false
            currentPosition = 0f
        }
    }
}


@Preview
@Composable
private fun AudioPreview() {
    EchoJournalTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Audio(
                modifier = Modifier.padding(innerPadding),
                timeProgress = "0:00/1:23",
                recordedFilePath = null
            )
        }
    }
}