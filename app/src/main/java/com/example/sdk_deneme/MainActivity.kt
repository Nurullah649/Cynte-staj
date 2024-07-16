@file:Suppress("NAME_SHADOWING")

package com.example.sdk_deneme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.example_sdk.ProjectInformationSDK
import com.example.sdk_deneme.ui.theme.SDK_denemeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SDK_denemeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    var display by remember { mutableStateOf("") }
    val sdk = ProjectInformationSDK()

    Column(
        modifier = Modifier.padding(25.dp)
    ) {
        Text(
            text = display,
            modifier = modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        val modifier = Modifier
            .width(70.dp)
            .height(55.dp)

        val buttonTextSize = 10.sp

        val onClickNumber = { number: String ->
            display += number
        }

        val onClickOperation = { operation: String ->
            display += " $operation "
        }

        val onClickClear = { text: String ->
            display = ""
        }

        val onClickEqual = {
            val tokens = display.split(" ")
            if (tokens.size == 3) {
                val a = tokens[0].toDoubleOrNull()
                val b = tokens[2].toDoubleOrNull()
                val result = if (a != null && b != null) {
                    try {
                        when (tokens[1]) {
                            "+" -> sdk.add(a, b)
                            "-" -> sdk.subtract(a, b)
                            "*" -> sdk.multiply(a, b)
                            "/" -> sdk.divide(a, b)
                            else -> null
                        }
                    } catch (e: IllegalArgumentException) {
                        null
                    }
                } else null
                display = result?.toString() ?: "Error"
            } else {
                display = "Error"
            }
        }
        Row (
            modifier = Modifier.padding(16.dp)
        ){
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { onClickClear(display) }, modifier = modifier) {
                Text(text = "AC")
            }
            Spacer(modifier = Modifier.width(0.dp))
            ToggleButton()
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { onClickOperation("%") }, modifier = modifier) {
                Text(text = "%")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { onClickOperation("/") }, modifier = modifier) {
                Text(text = "/")
            }
        }

        CalculatorButtonRow(
            buttons = listOf("7", "8", "9", "x"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            modifier = modifier,
            buttonTextSize = buttonTextSize
        )

        CalculatorButtonRow(
            buttons = listOf("4", "5", "6", "-"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            modifier = modifier,
            buttonTextSize = buttonTextSize
        )
        CalculatorButtonRow(
            buttons = listOf("1", "2", "3", "+"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            modifier = modifier,
            buttonTextSize = buttonTextSize
        )
        Row (
            modifier = Modifier.padding(16.dp)
        ){

            Button(onClick = { onClickNumber("0") }, modifier = Modifier
                .width(150.dp)
                .height(55.dp)) {
                Text(
                    text = "0                             ",
                    fontSize = buttonTextSize
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { onClickNumber(".") }, modifier = Modifier
                .width(70.dp)
                .height(55.dp)) {
                Text(
                    text = ".",
                    fontSize = buttonTextSize
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(onClick = { onClickEqual()}, modifier = Modifier
                .width(70.dp)
                .height(55.dp)) {
                Text(
                    text = "=",
                    fontSize = buttonTextSize
                )
            }
        }

    }
}
@Composable
fun ToggleButton() {
    // State to keep track of the current text
    var isPlus by remember { mutableStateOf(true) }

    // Function to toggle the text
    fun toggleText() {
        isPlus = !isPlus
    }

    // Composable content
    Spacer(modifier = Modifier.width(5.dp))
    Button(onClick = { toggleText() }, modifier = Modifier
        .width(70.dp)
        .height(55.dp)) {
        Text(text = "+/-")
    }
}
@Composable
fun CalculatorButtonRow(
    buttons: List<String>,
    onClickNumber: (String) -> Unit,
    onClickOperation: (String) -> Unit,
    onClickEqual: (() -> Unit)? = null,
    modifier: Modifier,
    buttonTextSize: androidx.compose.ui.unit.TextUnit
) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        buttons.forEach { button ->
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    when (button) {
                        in "0".."9", "." -> onClickNumber(button)
                        "=" -> onClickEqual?.invoke()
                        else -> onClickOperation(button)
                    }
                },
                modifier = modifier
            ) {
                Text(
                    text = button,
                    fontSize = buttonTextSize
                )
            }
        }
    }
}
@Composable
fun CalculatorButtonColumn(
    buttons: List<String>,
    onClickNumber: (String) -> Unit,
    onClickOperation: (String) -> Unit,
    onClickEqual: (() -> Unit)? = null,
    modifier: Modifier,
    buttonTextSize: androidx.compose.ui.unit.TextUnit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        buttons.forEach { button ->
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    when (button) {
                        in "0".."9", "." -> onClickNumber(button)
                        "=" -> onClickEqual?.invoke()
                        else -> onClickOperation(button)
                    }
                },
                modifier = modifier
            ) {
                Text(
                    text = button,
                    fontSize = buttonTextSize
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    SDK_denemeTheme {
        CalculatorApp()
    }
}
