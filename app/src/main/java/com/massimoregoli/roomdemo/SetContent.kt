package com.massimoregoli.roomdemo

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.massimoregoli.roomdemo.db.DbProverb
import com.massimoregoli.roomdemo.db.Repository
import com.massimoregoli.roomdemo.ui.theme.RoomDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("SwitchIntDef")
@Composable
fun Content() {
    var proverb by rememberSaveable { mutableStateOf("") }
    var filter by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val db = DbProverb.getInstance(context)
    val repository = Repository(db.proverbDao())
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            RoomDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black // MaterialTheme.colorScheme.background
                ) {
                    ShowProverbPortrait(proverb, filter, onChangeName = {filter=it}) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val p = repository.readFilteredNext("%$it%", 0)
                            proverb = p?.text ?: "I can't find any proverb with this word‼️"
                        }
                    }
                }
            }
        }

        Configuration.ORIENTATION_LANDSCAPE -> {
            RoomDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF00008B)// MaterialTheme.colorScheme.background
                ) {
                    ShowProverbLandscape(proverb, filter, onChangeName = {filter=it}) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val p = repository.readFilteredNext("%$it%", 0)
                            proverb = p?.text ?: "I can't find any proverb with this word‼️"
                        }
                    }
                }
            }
        }
    }
}