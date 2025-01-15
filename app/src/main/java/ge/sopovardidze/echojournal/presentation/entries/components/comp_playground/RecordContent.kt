package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import ExpandableText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.core.dropShadow
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType
import ge.sopovardidze.echojournal.presentation.entries.model.RecordModel
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30
import ge.sopovardidze.echojournal.ui.theme.PeacefulEnd
import ge.sopovardidze.echojournal.ui.theme.PeacefulStart
import ge.sopovardidze.echojournal.ui.theme.Shadow

@Composable
fun RecordContent(
    modifier: Modifier = Modifier,
    model: RecordModel,
    index: Int,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(10.dp),
                    color = Shadow.copy(0.2f),
                    blur = 20.dp,
                    offsetY = 6.dp
                )
                .background(
                    color = model.mood.gradientStartColor,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${model.title}",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "17:30",
                        style = MaterialTheme
                            .typography
                            .bodySmall
                            .copy(color = NeutralVariant30)
                    )
                }

                ExpandableText(
                    text = model.description,
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecordContentPreview() {
    EchoJournalTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val mockData = RecordModel(
                id = "1",
                date = 123123123123,
                mood = FilterType.Mood(
                    title = "Peaceful",
                    icon = R.drawable.ic_mood_peaceful,
                    isSelected = false,
                    gradientStartColor = PeacefulStart,
                    gradientEndColor = PeacefulEnd
                ),
                topics = emptyList(),
                title = "It was interesting",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel lacus sed lorem convallis dictum." +
                        "Donec eget leo at erat condimentum non eu risus. Aenean efficitur, ligula sed scelerisque" +
                        "mollis, libero felis congue mauris, in facilisis risus est a ligula. Vestibulum ac metus at libero" +
                        " luctus dignissim a sed nisl."
            )
            RecordContent(index = 2, model = mockData)
        }
    }
}