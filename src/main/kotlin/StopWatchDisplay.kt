import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StopWatchDisplay(
    modifier: Modifier = Modifier,
    formattedTime: String,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = formattedTime)
        Spacer(modifier = Modifier.size(36.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(onClick = onStartClick) {
                Text("Start")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = onPauseClick) {
                Text("Pause")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = onResetClick) {
                Text("Reset")
            }
        }
    }
}