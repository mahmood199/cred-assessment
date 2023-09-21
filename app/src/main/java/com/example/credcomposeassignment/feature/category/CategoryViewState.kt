package com.example.credcomposeassignment.feature.category

data class CategoryViewState(
    var isLoading : Boolean = false,
    var layoutType: LayoutType = LayoutType.Linear
)


sealed class LayoutType {
    data object Linear : LayoutType()
    data object Grid : LayoutType()
}