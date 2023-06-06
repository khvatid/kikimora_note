package khvatid.androidAi.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListItem(
   leadingIcon : @Composable() (BoxScope.()->Unit)?
) {
   Row {
      Box(modifier = Modifier.weight(0.2f), content = leadingIcon!!)

   }


}