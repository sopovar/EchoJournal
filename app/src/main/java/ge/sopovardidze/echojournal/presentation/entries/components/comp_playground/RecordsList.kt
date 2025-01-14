package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ge.sopovardidze.echojournal.core.Constants.mockDataList
import ge.sopovardidze.echojournal.presentation.entries.model.formattedDate
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme

@Composable
fun RecordsList(
    modifier: Modifier = Modifier
) {
    val groupedEntries = mockDataList.groupBy { it.formattedDate() }

    LazyColumn {
        groupedEntries.forEach { (header, entries) ->
            item {
                RecordHeader(
                    title = header
                )
            }
            items(entries.size) { index ->
                val entry = entries[index]
                RecordContent(
                    model = entry
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecordsListPrev() {
    EchoJournalTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            RecordsList()
        }
    }
}