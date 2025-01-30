package ge.sopovardidze.echojournal.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ge.sopovardidze.echojournal.presentation.create_record.CreateRecordActions
import ge.sopovardidze.echojournal.presentation.create_record.CreateRecordScreen
import ge.sopovardidze.echojournal.presentation.create_record.CreateRecordViewModel
import ge.sopovardidze.echojournal.presentation.records.RecordListAction
import ge.sopovardidze.echojournal.presentation.records.RecordsScreen
import ge.sopovardidze.echojournal.presentation.records.RecordsViewModel

@Composable
fun RootHost(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
) {
    val rootController = rememberNavController()

    NavHost(
        navController = rootController,
        startDestination = Records
    ) {
        composable<Records> {
            val recordsViewModel = hiltViewModel<RecordsViewModel>()
            val state = recordsViewModel.state.collectAsStateWithLifecycle()
            RecordsScreen(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                state = state.value,
                onAction = {
                    when (it) {
                        is RecordListAction.OnStartNewRecord -> {
                            rootController.navigate(
                                route = CreateRecord(it.filePath)
                            )
                        }

                        else -> {
                            recordsViewModel.onAction(it)
                        }
                    }
                },
            )
        }

        composable<CreateRecord> { backStackEntry ->
            val createRecordViewModel = hiltViewModel<CreateRecordViewModel>()
            val createRecord: CreateRecord = backStackEntry.toRoute()
            val state = createRecordViewModel.state.collectAsStateWithLifecycle()
            createRecordViewModel.setAudio(createRecord.filePath)
            CreateRecordScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                createRecord = createRecord,
                state = state.value,
                onAction = { action ->
                    when (action) {
                        is CreateRecordActions.NewMoodSelected -> {
                            createRecordViewModel.setMood(action.mood)
                        }
                        is CreateRecordActions.SetTitle -> {
                            createRecordViewModel.setTitle(action.title)
                        }
                        is CreateRecordActions.SetDescription -> {
                            createRecordViewModel.setDescription(action.description)
                        }
                        is CreateRecordActions.SetSelectedTopics -> {
                            createRecordViewModel.setTopics(action.topics)
                        }
                    }
                }
            )
        }
    }
}