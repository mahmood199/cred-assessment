package com.example.credcomposeassignment.feature.category

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.credcomposeassignment.data.models.CategoryItem
import com.example.credcomposeassignment.data.models.Section
import com.example.credcomposeassignment.feature.common.GridItem
import com.example.credcomposeassignment.feature.common.ListItem
import com.example.credcomposeassignment.ui.theme.CredComposeAssignmentTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CategoryPageUI(
    viewModel: CategoryViewModel,
    closeCategorySelection: () -> Unit,
    modifier: Modifier = Modifier
) {

    val sections by viewModel.sections.collectAsState()
    val state by viewModel.state.collectAsState()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.surface
    )

    BackHandler(enabled = true) {
        closeCategorySelection()
    }

    Scaffold(
        topBar = {
            if (state.isLoading.not()) {
                CategoryHeader(
                    state = state,
                    switchLayout = {
                        viewModel.updateLayout(state.layoutType)
                    },
                    closeCategorySelection = {
                        closeCategorySelection()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                )
            }
        },
        bottomBar = {
            if (state.isLoading.not()) {
                Button(
                    onClick = {
                        closeCategorySelection()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    Text(text = "Done with Selection")
                }
            }
        },
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
    ) { paddingValues ->
        AnimatedContent(
            targetState = state.isLoading,
            label = "Loader Animation",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {
            if (it) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                Column {
                    ListItems(
                        state = state,
                        sections = sections,
                    ) { item ->
                        viewModel.selectItem(item)
                    }
                }
            }
        }
    }
}


@Composable
fun ListItems(
    state: CategoryViewState,
    sections: List<Section>,
    itemSelected: (CategoryItem) -> Unit
) {
    val lazyGridScope1 = rememberLazyGridState()
    val lazyGridScope2 = rememberLazyGridState()

    val columns = when (state.layoutType) {
        LayoutType.Grid -> 3
        LayoutType.Linear -> 1
    }

    val gridPaddingValues = remember {
        PaddingValues(
            vertical = 12.dp,
            horizontal = 20.dp
        )
    }

    LazyVerticalGrid(
        state = if (columns == 3) lazyGridScope1
        else lazyGridScope2,
        contentPadding = gridPaddingValues,
        columns = GridCells.Fixed(
            columns
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GridSetup(
            sections = sections,
            columns = columns
        ) { item ->
            itemSelected(item)
        }
    }
}

@Composable
fun CategoryHeader(
    state: CategoryViewState,
    switchLayout: () -> Unit,
    closeCategorySelection: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "explore",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Cred",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Switch(
            checked = state.layoutType == LayoutType.Linear,
            onCheckedChange = {
                switchLayout()
            }
        )
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier.clickable {
                closeCategorySelection()
            }
        )
    }
}

fun LazyGridScope.GridSetup(
    sections: List<Section>,
    columns: Int,
    itemSelected: (CategoryItem) -> Unit
) {
    GridArrangement(
        columns = columns,
        sections = sections
    ) { categoryItem ->
        itemSelected(categoryItem)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyGridScope.GridArrangement(
    columns: Int,
    sections: List<Section>,
    itemSelected: (CategoryItem) -> Unit
) {
    sections.forEachIndexed { index, section ->
        item(key = section.id + index, span = {
            GridItemSpan(currentLineSpan = columns)
        }) {
            Text(
                text = section.sectionProperty.title,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        items(
            items = section.sectionProperty.categories,
            key = { categoryItem ->
                categoryItem.id + index
            }
        ) { categoryItem ->
            Crossfade(
                targetState = columns,
                label = "Label",
                modifier = Modifier.animateItemPlacement()
            ) {
                when (it) {
                    1 -> {
                        ListItem(
                            categoryProperty = categoryItem.categoryProperty,
                            onClick = {
                                itemSelected(categoryItem)
                            },
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    3 -> {
                        GridItem(
                            categoryProperty = categoryItem.categoryProperty,
                            onClick = {
                                itemSelected(categoryItem)
                            },
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryPageUIPreview() {
    val viewModel = CategoryViewModel()
    CredComposeAssignmentTheme {
        CategoryPageUI(
            viewModel = viewModel,
            closeCategorySelection = {}
        )
    }
}