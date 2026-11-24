package com.farimarwat.krossuidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            // adb shell am start -n com.farimarwat.krossuidemo/.MainActivity -e benchmark native|material
            App(benchmark = intent.getStringExtra("benchmark"))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
