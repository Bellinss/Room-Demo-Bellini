package com.massimoregoli.roomdemo

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.massimoregoli.roomdemo.ui.theme.bigFontSizeLandscape
import com.massimoregoli.roomdemo.ui.theme.fontSizeLandscape
import com.massimoregoli.roomdemo.ui.theme.iconSize
import com.massimoregoli.roomdemo.ui.theme.lineHeight
import com.massimoregoli.roomdemo.ui.theme.smallPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowProverbLandscape(text: String, filter: String, onChangeName: (String) -> Unit,
                         onclick: (filter: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(text = stringResource(id = R.string.title),
                modifier = Modifier
                    .width(775.dp)
                    .padding(smallPadding)
                    .border(5.dp, Color.Red, RoundedCornerShape(20.dp)),
                textAlign = TextAlign.Center,
                fontSize = bigFontSizeLandscape,
                fontStyle = FontStyle.Italic,
                color = Color.Red,
                lineHeight = lineHeight,
                fontFamily = titleFont()
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = filter,
                onValueChange = {
                    onChangeName(it)
                },
                placeholder = {
                    Text(text = "Search", fontSize = fontSizeLandscape, fontFamily = bodyFont(),
                        color = Color.White)
                },
                modifier = Modifier
                    .padding(smallPadding)
                    .width(755.dp)
                    .align(Alignment.End)
                    .border(3.dp, Color.White, RoundedCornerShape(20.dp)),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onclick(filter)
                }),
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color(0xFF00008B),
                    unfocusedContainerColor = Color(0xFF00008B)
                ),
                textStyle = TextStyle.Default.copy(fontSize = fontSizeLandscape, fontFamily = bodyFont()),
                trailingIcon = {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize, iconSize)
                            .clickable {
                                onclick(filter)
                                keyboardController?.hide()
                            })
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = if (text == "") {
                stringResource(id = R.string.message)
            } else {
                text
            },

            modifier = Modifier
                .clickable {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onclick(filter)
                }

                .fillMaxWidth()
                .padding(smallPadding)
                .border(3.dp, Color.Red, RoundedCornerShape(20.dp))
                .padding(smallPadding * 2)
                .defaultMinSize(minHeight = 80.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            color = Color.Red,
            textAlign = TextAlign.Center,
            fontSize = fontSizeLandscape,
            fontStyle = FontStyle.Italic,
            lineHeight = lineHeight,
            fontFamily = bodyFont()
        )
    }
}