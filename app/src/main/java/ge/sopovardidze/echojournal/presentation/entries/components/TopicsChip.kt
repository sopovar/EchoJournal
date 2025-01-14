package ge.sopovardidze.echojournal.presentation.entries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.core.Constants.mockTopics
import ge.sopovardidze.echojournal.presentation.entries.model.FilterType
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Secondary10

@Composable
fun TopicsChip(
    modifier: Modifier = Modifier,
    selectedTopics: List<FilterType.Topics>,
    isChipActive: Boolean = true,
    onTopicClick: () -> Unit,
    onClearAll: () -> Unit,
) {
    GeneralChip(
        modifier = modifier,
        title = "All Topics",
        isSelected = isChipActive,
        isEmpty = selectedTopics.isEmpty(),
        onChipClick = onTopicClick,
        onClearAll = onClearAll,
    ) {
        val selectedTopicsTitles = selectedTopics.filter { it.isSelected }.map { it.title }
        val selectedTopicsTitle = when {
            selectedTopicsTitles.isEmpty() -> ""
            selectedTopicsTitles.size <= 2 -> selectedTopicsTitles.joinToString(", ")
            else -> {
                val visibleItems = selectedTopicsTitles.take(2).joinToString(", ")
                "$visibleItems +${selectedTopicsTitles.size - 2}"
            }
        }
        Text(
            text = selectedTopicsTitle,
            color = Secondary10
        )
    }
}

@Preview
@Composable
private fun TopicsChipPreview() {
    EchoJournalTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(24.dp)
            ) {
                TopicsChip(
                    selectedTopics = mockTopics,
                    isChipActive = false,
                    onTopicClick = {},
                    onClearAll = {},
                )
            }
        }
    }
}