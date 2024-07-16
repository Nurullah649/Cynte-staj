package com.example.sdk_deneme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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

        val buttonModifier = Modifier
            .width(60.dp)
            .height(40.dp)

        val buttonTextSize = 10.sp

        val onClickNumber = { number: String ->
            display += number
        }

        val onClickOperation = { operation: String ->
            display += " $operation "
        }

        val onClickClear = {
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

        CalculatorButtonRow(
            buttons = listOf("7", "8", "9", "/"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            buttonModifier = buttonModifier,
            buttonTextSize = buttonTextSize
        )
        CalculatorButtonRow(
            buttons = listOf("4", "5", "6", "*"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            buttonModifier = buttonModifier,
            buttonTextSize = buttonTextSize
        )
        CalculatorButtonRow(
            buttons = listOf("1", "2", "3", "-"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            buttonModifier = buttonModifier,
            buttonTextSize = buttonTextSize
        )
        CalculatorButtonRow(
            buttons = listOf("0", ".", "=", "+"),
            onClickNumber = onClickNumber,
            onClickOperation = onClickOperation,
            onClickEqual = onClickEqual,
            buttonModifier = buttonModifier,
            buttonTextSize = buttonTextSize
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClickClear,
            modifier = buttonModifier
        ) {
            Text(
                text = "C",
                fontSize = buttonTextSize
            )
        }
    }
}

@Composable
fun CalculatorButtonRow(
    buttons: List<String>,
    onClickNumber: (String) -> Unit,
    onClickOperation: (String) -> Unit,
    onClickEqual: (() -> Unit)? = null,
    buttonModifier: Modifier,
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
                        "=", "+" , "-", "*", "/" -> onClickEqual?.invoke() ?: onClickOperation(button)
                        else -> onClickOperation(button)
                    }
                },
                modifier = buttonModifier
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
