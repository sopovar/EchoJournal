package ge.sopovardidze.echojournal.presentation.entries.components.comp_playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.ui.theme.NeutralVariant30

@Composable
fun RecordHeader(
    modifier: Modifier = Modifier,
    title: String
) {
    Column(
        modifier = modifier.padding(top = 24.dp).fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(color = NeutralVariant30)
        )
    }
}