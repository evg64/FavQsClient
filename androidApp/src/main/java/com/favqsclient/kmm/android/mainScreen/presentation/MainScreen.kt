package com.favqsclient.kmm.android.mainScreen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ChipDefaults.chipColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.favqsclient.kmm.android.R
import com.favqsclient.kmm.android.login.presentation.LoginViewModel
import com.favqsclient.kmm.android.mainScreen.model.Quote

/**
 *
 *
 * @author Sergey Ozerny
 **/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    scaffoldState: ScaffoldState,
){
    val quotes = viewModel.getQuotes()
    Scaffold(
        topBar = {
            MainTopBar()
        },
        bottomBar = {
            MainBottomBar()
        }
    ){
        QuoteList(quotes)
    }
}

@Preview
@Composable
fun MainTopBar(){
    TopAppBar(
        backgroundColor = Color.White
    ) {
        IconButton(onClick = { }) { Icon(Icons.Filled.Search, contentDescription = "Поиск", tint = Color.Black ) }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { }) {Icon(Icons.Filled.MoreHoriz, contentDescription = "Меню", tint = Color.Black) }
    }
}

@Preview
@Composable
fun MainBottomBar(){
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            label = {Text("Главная")},
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            icon = { Icon(painter = painterResource(id = R.drawable.main_icon), contentDescription = "Главная", tint = Color.Black)}
        )
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            label = {Text("Личный кабинет")},
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            icon = { Icon(Icons.Filled.Person, contentDescription = "Личный кабинет", tint = Color.Black)}
        )
    }
}



val chipBackgroundColor = Color(0F, 0F, 0F, 0.08F)
val authorTextColor = Color(0F,0F,0F,0.55F)
val holderFontSizeBig = 15.sp
val holderFontSizeSmall = 13.sp

@Composable
fun QuoteList(quotes: List<Quote>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = quotes) { quote ->
            QuoteCard(quote = quote)
        }
    }
}

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
                    fontSize = holderFontSizeBig
                )
                Text(
                    text = quote.author,
                    fontSize = holderFontSizeSmall,
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
                        fontSize = holderFontSizeSmall
                    )
                }
            }
        }
        Divider(
            color = chipBackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
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
                    fontSize = holderFontSizeBig
                )
                Text(
                    text = quote.author,
                    fontSize = holderFontSizeSmall,
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
                        fontSize = holderFontSizeSmall
                    )
                }
            }
        }
        Divider(
            color = chipBackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}


