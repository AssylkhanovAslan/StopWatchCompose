import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


private const val FOMRATTED_TIME_INITIAL_VALUE = "00:00:000"
class StopWatch {


    var formattedTime by mutableStateOf(FOMRATTED_TIME_INITIAL_VALUE)
    val timeFormatter = DateTimeFormatter.ofPattern("mm:ss:SSS", Locale.getDefault())

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isOn = false

    private var timeMillis = 0L
    private var lastTimestamp = 0L


    fun start() {
        if (isOn) {
            return
        }

        lastTimestamp = System.currentTimeMillis()
        isOn = true

        coroutineScope.launch {
            while (isOn) {
                delay(10L)
                val currentTimeMillis = System.currentTimeMillis()
                timeMillis += currentTimeMillis - lastTimestamp
                lastTimestamp = currentTimeMillis

                formattedTime = formatTime(timeMillis)
            }
        }
    }

    fun pause() {
        isOn = false
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)

        timeMillis = 0L
        lastTimestamp = 0L
        formattedTime = FOMRATTED_TIME_INITIAL_VALUE
        isOn = false
    }

    private fun formatTime(timeMillis: Long): String {
        val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault())
        return localDateTime.format(timeFormatter)
    }
}