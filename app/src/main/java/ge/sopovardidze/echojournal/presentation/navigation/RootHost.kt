package ge.sopovardidze.echojournal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ge.sopovardidze.echojournal.presentation.create_record.CreateRecordScreen
import ge.sopovardidze.echojournal.presentation.records.RecordsScreen
import ge.sopovardidze.echojournal.presentation.records.RecordsViewModel
import ge.sopovardidze.echojournal.ui.theme.PurpleGrey80

@Composable
fun RootHost(
    modifier: Modifier = Modifier,
    recordsViewModel: RecordsViewModel,
    innerPadding: PaddingValues,
) {
    val rootController = rememberNavController()

    NavHost(
        navController = rootController,
        startDestination = Records
    ) {
        composable<Records> {
            val state = recordsViewModel.state.collectAsStateWithLifecycle()
            RecordsScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                state = state.value,
                onAction = {
                    recordsViewModel.onAction(it)
                },
                onStartNewRecord = { filePath ->
                    rootController.navigate(
                        route = CreateRecord(filePath)
                    )
                }
            )
        }

        composable<CreateRecord> { backStackEntry ->
            val createRecord: CreateRecord = backStackEntry.toRoute()
            CreateRecordScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PurpleGrey80)
                    .padding(innerPadding),
                createRecord = createRecord
            )
        }
    }
}