package ge.sopovardidze.echojournal.presentation.entries

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.sopovardidze.echojournal.R
import ge.sopovardidze.echojournal.presentation.entries.components.comp_playground.RecordsList
import ge.sopovardidze.echojournal.presentation.entries.model.EntriesUiState
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme

@Composable
fun EntriesListScreen(
    modifier: Modifier = Modifier,
    state: EntriesUiState,
    onAction: (EntriesListAction) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your EchoJournal",
                    style = MaterialTheme.typography.headlineLarge
                )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                    contentDescription = "Settings"
                )
            }
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    onAction.invoke(EntriesListAction.OnFabClick)
                },
                shape = CircleShape,
                containerColor = Color.Transparent,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_fab_add),
                    contentDescription = "FabAdd",
                )
            }
        }
    ) { padding ->
        if (state.pageIsEmpty) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_empty),
                    contentDescription = "Empty"
                )
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "No Entries",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Start recording your first Echo ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
//                EchoFilter(
//                    state = state,
//                    action = {
//                        onAction.invoke(it)
//                    }
//                )
                RecordsList()
            }
        }
    }
}


@Preview
@Composable
private fun EntriesListScreenPreview() {
    EchoJournalTheme {
        EntriesListScreen(
            state = EntriesUiState(),
            onAction = {

            }
        )
    }
}