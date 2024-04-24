package com.massimoregoli.roomdemo

import android.annotation.SuppressLint

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

class MainActivity : ComponentActivity() {
    @SuppressLint("SwitchIntDef")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { Content() }
    }
}

@Composable
fun titleFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("pricedown bl.otf", assets)
    )
}

@Composable
fun bodyFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("Sedan-Regular.ttf", assets)
    )
}
