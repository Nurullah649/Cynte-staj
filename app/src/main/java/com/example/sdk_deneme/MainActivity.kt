package com.example.sdk_deneme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.example_sdk.Project_Information_SDK
import com.example.sdk_deneme.ui.theme.SDK_denemeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SDK_denemeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val sdkInfo = Project_Information_SDK()
    val version = sdkInfo.sdkVersion
    val sdkName = sdkInfo.creatorname

    Tab(selected = true, onClick = { /*TODO*/ }) {
        
    }
    Text(
        text = "SDK Version: $version\nSDK Creator: $sdkName",
        modifier=modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SDK_denemeTheme {
        Greeting("Android")
    }
}