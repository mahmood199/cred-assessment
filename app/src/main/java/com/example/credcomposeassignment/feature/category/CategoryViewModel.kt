package com.example.credcomposeassignment.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.credcomposeassignment.data.models.CategoryItem
import com.example.credcomposeassignment.data.models.Section
import com.example.credcomposeassignment.data.remote.DataSourceImpl
import com.example.credcomposeassignment.data.remote.SectionServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val dataSource = DataSourceImpl(SectionServiceImpl())

    private val _sections = MutableStateFlow(emptyList<Section>())
    val sections = _sections.asStateFlow()

    private val _state = MutableStateFlow(CategoryViewState())
    val state = _state.asStateFlow()

    private val _selectedCategories = MutableStateFlow(mutableListOf<CategoryItem>())
    val selectedCategories = _selectedCategories.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            _sections.value = dataSource.getCategories().section + dataSource.getCategories().section
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun updateLayout(layoutType: LayoutType) {
        _state.value = _state.value.copy(
            layoutType = when(layoutType) {
                LayoutType.Grid -> LayoutType.Linear
                LayoutType.Linear -> LayoutType.Grid
            }
        )
    }

    fun selectItem(item: CategoryItem) {
        val originalList = _selectedCategories.value
        if(_selectedCategories.value.contains(item)) {
            originalList.remove(item)
        } else {
            originalList.add(item)
        }
        _selectedCategories.value = originalList
    }

}
