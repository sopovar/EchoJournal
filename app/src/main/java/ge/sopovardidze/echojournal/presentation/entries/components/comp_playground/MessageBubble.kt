package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Pink80
import ge.sopovardidze.echojournal.ui.theme.Purple80
import ge.sopovardidze.echojournal.ui.theme.Secondary50
import ge.sopovardidze.echojournal.ui.theme.Secondary80

@Composable
private fun MessageBubble(modifier: Modifier, containerColor: Color) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun TimelinePreview() {
    EchoJournalTheme {
        val radius = 16.dp
        val timelineNodes = listOf(
            TimelineNodeData(
                circleParameters = CircleParameters(radius, Secondary50),
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = Secondary50,
                    endColor = Purple80
                ),
                containerColor = Secondary50
            ),
            TimelineNodeData(
                circleParameters = CircleParameters(radius, Purple80),
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = Purple80,
                    endColor = Secondary80
                ),
                containerColor = Purple80
            ),
            TimelineNodeData(
                circleParameters = CircleParameters(radius, Pink80),
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = Pink80,
                    endColor = Secondary80
                ),
                containerColor = Pink80
            ),
            TimelineNodeData(
                circleParameters = CircleParameters(radius, Secondary80),
                lineParameters = null,
                containerColor = Secondary80
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(timelineNodes.size) { index ->
                val node = timelineNodes[index]
                TimelineNode(
                    circleParameters = node.circleParameters,
                    lineParameters = node.lineParameters
                ) { modifier ->
                    MessageBubble(modifier, containerColor = node.containerColor)
                }
            }
        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            val radius = 12.dp
//            TimelineNode(
//                circleParameters = CircleParameters(radius, Secondary50),
//                lineParameters = LineParametersDefaults.linearGradient(
//                    startColor = Secondary50,
//                    endColor = Purple80
//                )
//            ) { modifier -> MessageBubble(modifier, containerColor = Secondary50) }
//            TimelineNode(
//                circleParameters = CircleParameters(radius, Purple80),
//                lineParameters = LineParametersDefaults.linearGradient(
//                    startColor = Purple80,
//                    endColor = Secondary80
//                )
//            ) { modifier -> MessageBubble(modifier, containerColor = Purple80) }
//            TimelineNode(
//                circleParameters = CircleParameters(radius, Secondary80),
//                lineParameters = null
//            ) { modifier -> MessageBubble(modifier, containerColor = Secondary80) }
//        }
    }
}