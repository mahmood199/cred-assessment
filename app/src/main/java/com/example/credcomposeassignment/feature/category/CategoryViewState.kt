package com.example.credcomposeassignment.feature.category

data class CategoryViewState(
    var isLoading : Boolean = false,
    var gridSpan: GridSpan = GridSpan.Single
)


sealed class GridSpan(val columns: Int) {
    data object Single : GridSpan(1)
    data object Triple : GridSpan(3)
}
