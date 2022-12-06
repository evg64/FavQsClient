package com.favqsclient.kmm.android.presentation

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ChipDefaults.chipColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.favqsclient.kmm.android.model.Quote

@Preview
@Composable
fun QuoteCardPreview(){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Новаторы не всегда в чести. Поначалу.")
        Text(text = "Джон Эдгар Гувер")
        Row() {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Жизненные цитаты")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "новаторство")
            }
        }

    }

}

val chipBackgroundColor = Color(0F, 0F, 0F, 0.08F)
val authorTextColor = Color(0F,0F,0F,0.55F)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuoteCard(quote: Quote){
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 12.dp)
    ) {
        Row(){
            Column( verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = quote.body,
                    fontSize = 15.sp
                )
                Text(
                    text = quote.author,
                    fontSize = 13.sp,
                    color = authorTextColor
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            quote.tags.forEach{
                Chip(
                    onClick = { /*TODO*/ },
                    colors = chipColors(
                        backgroundColor = chipBackgroundColor
                    )
                ) {
                    Text(
                        text = it,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun QuoteCardMock(){
    val quote = Quote.getMockQuote()
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 12.dp)
    ) {
        Row(){
            Column( verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = quote.body,
                    fontSize = 15.sp
                )
                Text(
                    text = quote.author,
                    fontSize = 13.sp,
                    color = authorTextColor
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            quote.tags.forEach{
                Chip(
                    onClick = { /*TODO*/ },
                    colors = chipColors(
                        backgroundColor = chipBackgroundColor
                    )
                ) {
                    Text(
                        text = it,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}