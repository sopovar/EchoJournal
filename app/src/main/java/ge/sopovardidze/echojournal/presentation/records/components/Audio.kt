package ge.sopovardidze.echojournal.presentation.records.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.core.noRippleClickable
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30
import ge.sopovardidze.echojournal.ui.theme.PeacefulStart
import ge.sopovardidze.echojournal.ui.theme.Shadow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Audio(
    modifier: Modifier = Modifier,
    color: Color,
    timeProgress: String,
    isPlaying: Boolean,
) {
    val shape = CircleShape
    val icon = remember {
        mutableStateOf(
            if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
    }
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(
                color = color.copy(alpha = 0.3f),
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
                .clip(shape = shape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(icon.value),
                contentDescription = "Play/Pause",
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.noRippleClickable {
                    loading = true
                    scope.launch {
                        loadProgress { progress ->
                            currentProgress = progress
                        }
                        loading = false
                    }
                }
            )
        }

        LinearProgressIndicator(
            progress = { currentProgress },
            color = color,
            trackColor = color.copy(alpha = 0.5f)
        )

        Text(
            text = timeProgress,
            style = MaterialTheme.typography.bodySmall.copy(
                color = NeutralVariant30
            )
        )
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Preview
@Composable
private fun AudioPreview() {
    EchoJournalTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Audio(
                modifier = Modifier.padding(innerPadding),
                color = PeacefulStart,
                timeProgress = "0:00/1:23",
                isPlaying = false
            )
        }
    }
}