package ge.sopovardidze.echojournal.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.echojournal.presentation.create_record.CreateRecordViewModel
import ge.sopovardidze.echojournal.presentation.navigation.RootHost
import ge.sopovardidze.echojournal.presentation.records.RecordsViewModel
import ge.sopovardidze.echojournal.ui.theme.EchoJournalTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            EchoJournalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val viewModel: RecordsViewModel by viewModels()
                    val createRecordViewModel: CreateRecordViewModel by viewModels()
                    RootHost(
                        recordsViewModel = viewModel,
                        createRecordViewModel = createRecordViewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}