package com.example.jetpackcomposeapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarouselDummy() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val pagerState = rememberPagerState { colors.size }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % colors.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors[page]),
            contentAlignment = Alignment.Center
        ) {
            Text("Banner ${page + 1}", color = Color.White)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteBannerCarouselDummy() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val virtualPages = 1000
    val startIndex = virtualPages / 2

    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { virtualPages }
    )

    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        val actualIndex = page % colors.size

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors[actualIndex]),
            contentAlignment = Alignment.Center
        ) {
            Text("Banner ${actualIndex + 1}", color = Color.White)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteBannerCarouselWithDots() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val virtualPages = 1000
    val startIndex = virtualPages / 2

    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { virtualPages }
    )

    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            val actualIndex = page % colors.size
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors[actualIndex]),
                contentAlignment = Alignment.Center
            ) {
                Text("Banner ${actualIndex + 1}", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Dot indicator
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val selectedIndex = pagerState.currentPage % colors.size
            colors.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (index == selectedIndex) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (index == selectedIndex) Color.DarkGray else Color.LightGray)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteBannerCarouselWithInteractiveDots() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val virtualPages = 1000
    val startIndex = virtualPages / 2

    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { virtualPages }
    )

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            val actualIndex = page % colors.size
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors[actualIndex]),
                contentAlignment = Alignment.Center
            ) {
                Text("Banner ${actualIndex + 1}", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Dot indicators with animation and click
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val selectedIndex = pagerState.currentPage % colors.size

            colors.forEachIndexed { index, _ ->
                val size by animateDpAsState(
                    targetValue = if (index == selectedIndex) 12.dp else 8.dp,
                    label = "DotSize"
                )
                val color by animateColorAsState(
                    targetValue = if (index == selectedIndex) Color.DarkGray else Color.LightGray,
                    label = "DotColor"
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(size)
                        .clip(CircleShape)
                        .background(color)
                        .clickable {
                            val actualTargetPage = pagerState.currentPage -
                                    (pagerState.currentPage % colors.size) + index

                            coroutineScope.launch {
                                pagerState.animateScrollToPage(actualTargetPage)
                            }
                        }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FocusedBannerCarouselWithPeekEffect() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val virtualPages = 1000
    val startIndex = virtualPages / 2

    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { virtualPages }
    )

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 30.dp), // Make space for left/right peek
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            val actualIndex = page % colors.size

            // Animate height based on current page offset
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = 1f - (0.1f * kotlin.math.abs(pageOffset))
            val height = 200.dp * scale.coerceAtLeast(0.85f) // Don't shrink too much

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .height(height)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors[actualIndex]),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Banner ${actualIndex + 1}",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dot indicators
        Row(horizontalArrangement = Arrangement.Center) {
            val selectedIndex = pagerState.currentPage % colors.size
            colors.forEachIndexed { index, _ ->
                val size by animateDpAsState(
                    targetValue = if (index == selectedIndex) 12.dp else 8.dp,
                    label = "DotSize"
                )
                val color by animateColorAsState(
                    targetValue = if (index == selectedIndex) Color.DarkGray else Color.LightGray,
                    label = "DotColor"
                )

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(size)
                        .clip(CircleShape)
                        .background(color)
                        .clickable {
                            val targetPage = pagerState.currentPage - (pagerState.currentPage % colors.size) + index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(targetPage)
                            }
                        }
                )
            }
        }
    }
}
