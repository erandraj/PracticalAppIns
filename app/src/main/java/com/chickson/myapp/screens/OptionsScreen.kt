import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun OptionsScreen(onNext: () -> Unit, onExit: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).testTag("OptionsScreen"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "test text 1",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("MainText")
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth().testTag("TestTextButton")
        ) {
            Text("Test text")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onExit,
            modifier = Modifier.fillMaxWidth().testTag("NotTestTextButton")
        ) {
            Text("Not test text")
        }
    }
}