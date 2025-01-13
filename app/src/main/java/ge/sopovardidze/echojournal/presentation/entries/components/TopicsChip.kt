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
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Secondary10

@Composable
fun TopicsChip(
    modifier: Modifier = Modifier,
    selectedTopics: List<String>,
    isSelected: Boolean = true,
    onTopicClick: () -> Unit,
    onClearAll: () -> Unit,
) {
    GeneralChip(
        modifier = modifier,
        title = "All Topics",
        isSelected = isSelected,
        isEmpty = selectedTopics.isEmpty(),
        onChipClick = onTopicClick,
        onClearAll = onClearAll,
    ) {
        val selectedTopicsTitle = when {
            selectedTopics.isEmpty() -> ""
            selectedTopics.size <= 2 -> selectedTopics.joinToString(", ")
            else -> {
                val visibleItems = selectedTopics.take(2).joinToString(", ")
                "$visibleItems +${selectedTopics.size - 2}"
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
                val mockMoodList = listOf(
                    "Family",
                    "Love",
                    "Work",
                    "Friends",
                )
                TopicsChip(
                    selectedTopics = mockMoodList,
                    isSelected = false,
                    onTopicClick = {},
                    onClearAll = {},
                )
            }
        }
    }
}