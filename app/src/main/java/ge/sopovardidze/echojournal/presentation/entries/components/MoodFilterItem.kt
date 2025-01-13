package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.entries.model.MoodModel
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Secondary10
import ge.sopovardidze.echojournal.ui.theme.SurfaceTint5

@Composable
fun MoodFilterItem(
    modifier: Modifier = Modifier,
    mood: MoodModel,
    onSelected: (Boolean) -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (mood.isSelected) SurfaceTint5.copy(alpha = 0.05f) else White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(mood.iconRes),
            contentDescription = mood.mood,
            modifier = Modifier
                .size(24.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = mood.mood,
            color = Secondary10
        )
        if (mood.isSelected) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun MoodFilterItemPreview() {
    EchoJournalTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(24.dp)
            ) {
                MoodFilterItem(
                    mood = MoodModel(
                        "Neutral",
                        R.drawable.ic_mood_neutral,
                        true
                    ), onSelected = {}
                )
            }
        }
    }
}