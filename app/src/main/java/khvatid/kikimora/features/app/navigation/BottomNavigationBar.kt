package khvatid.kikimora.features.app.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun BottomNavigationBar(
    tabs: List<AppDestination.NoArgumentsDestination>,
    currentRoute: String,
    isShow: Boolean,
    tabClick: (String) -> Unit
) {
    AnimatedVisibility(visible = isShow, exit = shrinkVertically(), enter = expandVertically()) {
        NavigationBar {
            tabs.forEach {
                NavigationBarItem(
                    selected = it() == currentRoute,
                    onClick = { tabClick(it()) },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = stringResource(
                                id = it.label
                            )
                        )
                    },
                    label = { Text(text = stringResource(id = it.label)) }
                )
            }
        }
    }
}