package com.bsoft.tabstest.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabExampleScene() {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }


    val tabTitles = listOf(
        TabExampleState.title1,
        TabExampleState.title2,
        TabExampleState.title3
    )
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {

            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectedTabIndex.value,
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Green,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = title) },
                )
            }
        }

        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) {
            when (pagerState.currentPage) {
                0 -> Tab1()
                1 -> Tab2()
                2 -> Tab3()
                else -> {}
            }
        }
    }
}


@Composable
fun Tab1() {
    Box(modifier = Modifier.background(color = Color.Red).fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = TabExampleState.title1)
    }
}

@Composable
fun Tab2() {
    Box(modifier = Modifier.background(color = Color.Green).fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = TabExampleState.title2)
    }
}

@Composable
fun Tab3() {
    Box(modifier = Modifier.background(color = Color.Blue).fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = TabExampleState.title3)
    }
}

data object TabExampleState {
    val title1: String = "Gamargomba"
    val title2: String = "Ahoj"
    val title3: String = "Hi"
}