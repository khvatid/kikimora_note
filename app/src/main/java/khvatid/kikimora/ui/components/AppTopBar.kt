package khvatid.kikimora.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khvatid.kikimora.ui.theme.spaceMono

@Composable
fun AppTopBar(menuClick: ()->Unit, settingsClick: ()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = menuClick) {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
        }
        Text(
            text = "Personal Assistant",
            fontFamily = spaceMono,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        IconButton(onClick = settingsClick) {
            Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
        }
    }
}