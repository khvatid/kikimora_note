package khvatid.kikimora.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.AndroidEntryPoint
import khvatid.kikimora.features.conversation.ConversationScreen
import khvatid.kikimora.features.notes.NotesScreen
import khvatid.kikimora.features.settings.SettingsScreen
import khvatid.kikimora.ui.components.AppTopBar
import khvatid.kikimora.ui.theme.AiTheme


@AndroidEntryPoint
class AppActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsState()
            AiTheme(dynamicColor = uiState.isDynamicTheme) {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            menuClick = {},
                            settingsClick = {
                                viewModel.obtainEvent(
                                    AppContract.Event.NavigateToSettings
                                )
                            }
                        )
                    },
                    contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .consumeWindowInsets(it),
                        navController = uiState.navController,
                        startDestination = "conversation",
                    ) {
                        composable(route = "conversation") {
                            ConversationScreen(viewModel = hiltViewModel())
                        }
                        composable(route = "settings") {
                            SettingsScreen(viewModel = hiltViewModel())
                        }
                        composable(route = "notes"){
                            NotesScreen(viewModel = hiltViewModel())
                        }
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
