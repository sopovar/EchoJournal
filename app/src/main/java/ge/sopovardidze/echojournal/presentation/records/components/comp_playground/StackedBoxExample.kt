package ge.sopovardidze.echojournal.presentation.records.components.comp_playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme
import ge.sopovardidze.echojournal.ui.theme.Pink80
import ge.sopovardidze.echojournal.ui.theme.Purple80

@Composable
fun StackedBoxExample(
    showTop: Boolean,
) {

    var chipsHeight by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Purple80)
                    .onSizeChanged { size ->
                        chipsHeight = size.height // Capture the height of the chips
                    }
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Topics row",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .height(100.dp)
                    .background(Pink80)
            ) {
                Text(
                    text = "Records list",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        if (showTop) {
            Text(
                text = "Top Start padding value = ${chipsHeight}",
                modifier = Modifier
                    .padding(top = with(LocalDensity.current) { chipsHeight.toDp() }) // Use the measured height dynamically
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
                showTop = true
            )
        }
    }
}