package khvatid.kikimora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import khvatid.kikimora.features.app.AppScreen
import khvatid.kikimora.features.app.AppViewModel


@AndroidEntryPoint
class AppActivity : ComponentActivity() {

   private val viewModel: AppViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         AppScreen(viewModel = viewModel)
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
