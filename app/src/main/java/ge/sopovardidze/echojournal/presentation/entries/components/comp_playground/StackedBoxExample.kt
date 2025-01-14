package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme

@Composable
fun StackedBoxExample(
    showTop: Boolean
) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan)
        ) {
            Text(
                text = "Second",
            )
        }

        if(showTop) {
            Text(
                text = "Top Start",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
                    .align(Alignment.TopStart)
            )
        }
    }
}

@Preview
@Composable
private fun StackedBoxExamplePreview() {
    EchoJournalTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            StackedBoxExample(
                showTop = false
            )
        }
    }
}