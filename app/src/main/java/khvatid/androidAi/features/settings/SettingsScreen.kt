package khvatid.androidAi.features.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.androidAi.features.settings.SettingsScreenContract.Events

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel = hiltViewModel()) {
   val state by viewModel.uiState.collectAsState()
   SettingsScreenUi(state = state, events = viewModel::obtainEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreenUi(
   state: SettingsScreenContract.State,
   events: (Events) -> Unit
) {
   Column {
      Row {
         TextField(
            value = state.token,
            onValueChange = { events(Events.OnTokenChanged(it)) },
            trailingIcon = {
               Icon(
                  modifier = Modifier.clickable { events(Events.AcceptTokenChanged) },
                  imageVector = Icons.Filled.Done,
                  contentDescription = null
               )
            }
         )
      }
      Button(onClick = { events(Events.UseDynamicTheme) }) {
         Text("Dynamic theme ")
      }
   }
}
