package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.core.Constants.mockDataList
import ge.sopovardidze.echojournal.presentation.entries.model.formattedDate
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Purple80
import ge.sopovardidze.echojournal.ui.theme.Secondary50

@Composable
fun RecordsList(
    modifier: Modifier = Modifier,
) {
    val groupedEntries = mockDataList.groupBy { it.formattedDate() }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        groupedEntries.forEach { (header, entries) ->
            item {
                RecordHeader(
                    title = header
                )
            }
            items(entries.size) { index ->
                val entry = entries[index]
                val lineParams = if(entries.lastIndex == index) null else LineParametersDefaults.linearGradient(
                    startColor = Secondary50,
                    endColor = Purple80
                )
                TimelineNode(
                    circleParameters = CircleParameters(icon = entry.mood.icon),
                    lineParameters = lineParams
                ) { modifier ->
                    RecordContent(
                        modifier = modifier,
                        model = entry,
                        index = index
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecordsListPrev() {
    EchoJournalTheme {
        Surface {
            RecordsList()
        }
    }
}