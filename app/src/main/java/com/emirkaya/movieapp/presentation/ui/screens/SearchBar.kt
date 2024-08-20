package com.emirkaya.movieapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.presentation.ui.theme.SearchBarDimensions

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    val textFieldValue = remember { mutableStateOf(TextFieldValue(query)) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SearchBarDimensions.paddingHorizontal, vertical = SearchBarDimensions.paddingVertical)
            .background(Color.Transparent),
        shape = CircleShape,
        elevation = SearchBarDimensions.elevation,

    ) {
        Box(
            modifier = Modifier
                .background(androidx.compose.material3.MaterialTheme.colorScheme.surface, CircleShape)
                .padding(horizontal = SearchBarDimensions.textFieldPaddingHorizontal, vertical = SearchBarDimensions.textFieldPaddingVertical)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(SearchBarDimensions.iconSize)
            )

            BasicTextField(
                value = textFieldValue.value,
                onValueChange = {
                    textFieldValue.value = it
                    onQueryChanged(it.text)
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.body1.copy(
                    fontSize = SearchBarDimensions.textSize,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = SearchBarDimensions.iconPaddingStart, end = SearchBarDimensions.iconPaddingEnd)
            )

            if (textFieldValue.value.text.isNotEmpty()) {
                IconButton(
                    onClick = { textFieldValue.value = TextFieldValue(""); onQueryChanged("") },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(SearchBarDimensions.clearIconSize)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = "Clear Icon",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

