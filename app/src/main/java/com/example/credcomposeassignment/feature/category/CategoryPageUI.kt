package com.example.credcomposeassignment.feature.category

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

@Composable
fun CategoryPageUI(
    viewModel: CategoryViewModel,
    closeCategorySelection: () -> Unit,
    modifier: Modifier = Modifier
) {

    val sections by viewModel.sections.collectAsState()
    val state by viewModel.state.collectAsState()

    BackHandler(enabled = true) {
        closeCategorySelection()
    }

    AnimatedContent(
        targetState = state.isLoading,
        label = "Loader Animation",
        modifier = modifier.fillMaxSize(),
    ) {
        if (it) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                ) {
                    CategoryHeader(
                        state = state,
                        switchLayout = {
                            viewModel.updateLayout(state.layoutType)
                        },
                        closeCategorySelection = {
                            closeCategorySelection()
                        }
                    )

                    ListItems(
                        state = state,
                        sections = sections,
                    ) { item ->
                        viewModel.selectItem(item)

                    }
                }
                Button(
                    onClick = {
                        closeCategorySelection()
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .fillMaxWidth()
                ) {
                    Text(text = "Done with Selection")
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
    val lazyListScope = rememberLazyListState()
    val lazyGridScope = rememberLazyGridState()

    val paddingValues = remember {
        PaddingValues(
            top = 12.dp,
            bottom = 40.dp,
            start = 8.dp,
            end = 8.dp
        )
    }

    when (state.layoutType) {
        LayoutType.Linear -> {
            LazyColumn(
                state = lazyListScope,
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LinearSetup(
                    sections = sections,
                    itemSelected = { item ->
                        itemSelected(item)
                    },
                )
            }
        }

        LayoutType.Grid -> {
            LazyVerticalGrid(
                state = lazyGridScope,
                contentPadding = paddingValues,
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GridSetup(
                    sections = sections,
                ) { item ->
                    itemSelected(item)
                }
            }
        }
    }
}

@Composable
fun CategoryHeader(
    state: CategoryViewState,
    switchLayout: () -> Unit,
    closeCategorySelection: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 0.dp)
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

fun LazyListScope.LinearSetup(
    sections: List<Section>,
    itemSelected: (CategoryItem) -> Unit
) {
    LinearArrangement(
        sections = sections
    ) { categoryItem ->
        itemSelected(categoryItem)
    }
}

fun LazyListScope.LinearArrangement(
    sections: List<Section>,
    itemSelected: (CategoryItem) -> Unit
) {
    sections.forEachIndexed { index, section ->
        item(key = section.id + index) {
            Text(
                text = section.sectionProperty.title,
                fontWeight = FontWeight.ExtraBold
            )
        }
        items(
            items = section.sectionProperty.categories,
            key = { categoryItem ->
                categoryItem.id + index
            }
        ) { categoryItem ->
            ListItem(
                categoryProperty = categoryItem.categoryProperty,
                onClick = {
                    itemSelected(categoryItem)
                }
            )
        }
    }
}

fun LazyGridScope.GridSetup(
    sections: List<Section>,
    itemSelected: (CategoryItem) -> Unit
) {
    GridArrangement(
        sections = sections
    ) { categoryItem ->
        itemSelected(categoryItem)
    }
}

fun LazyGridScope.GridArrangement(
    sections: List<Section>,
    itemSelected: (CategoryItem) -> Unit
) {
    sections.forEachIndexed { index, section ->
        item(key = section.id + index, span = {
            GridItemSpan(3)
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
            GridItem(
                categoryProperty = categoryItem.categoryProperty,
                onClick = {
                    itemSelected(categoryItem)
                }
            )
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