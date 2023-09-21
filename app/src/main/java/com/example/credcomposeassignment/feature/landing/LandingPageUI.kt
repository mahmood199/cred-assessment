package com.example.credcomposeassignment.feature.landing

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.credcomposeassignment.data.models.CategoryItem
import com.example.credcomposeassignment.feature.category.CategoryViewModel
import com.example.credcomposeassignment.feature.common.ListItem
import com.example.credcomposeassignment.ui.theme.CredComposeAssignmentTheme

@Composable
fun LandingPageUI(
    viewModel: CategoryViewModel,
    navigateToCategoryScreen: () -> Unit
) {

    val selectedItems by viewModel.selectedCategories.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {},
        bottomBar = {
            Button(
                onClick = {
                    navigateToCategoryScreen()
                },
                shape = RectangleShape,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Go to category", modifier = Modifier
                        .padding(end = 12.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
            ) {
                AnimatedContent(
                    targetState = selectedItems.size == 0,
                    label = "Selection Mode transition animation",
                    modifier = Modifier.fillMaxSize(),
                ) {
                    if (it) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Please select categories",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }
                    } else {
                        SelectedItems(selectedItems = selectedItems)
                    }
                }
            }
        }
    )
}

@Composable
fun SelectedItems(
    selectedItems: MutableList<CategoryItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(
            vertical = 20.dp,
            horizontal = 12.dp
        )
    ) {
        items(
            items = selectedItems,
            key = { categoryItem ->
                categoryItem.id
            }
        ) { categoryItem ->
            ListItem(categoryProperty = categoryItem.categoryProperty) {
            }
        }
    }
}

@Preview
@Composable
fun LandingPageUIPreview() {
    val viewModel = CategoryViewModel()
    CredComposeAssignmentTheme {
        LandingPageUI(viewModel = viewModel) {

        }
    }
}