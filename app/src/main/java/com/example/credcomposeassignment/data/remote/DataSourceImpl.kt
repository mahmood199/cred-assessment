package com.example.credcomposeassignment.data.remote

import com.example.credcomposeassignment.data.models.SectionResponse
import kotlinx.coroutines.delay

class DataSourceImpl(
    private val sectionService: SectionService
) : DataSource {

    override suspend fun getCategories(): SectionResponse {
        delay(2000)
        return sectionService.getSections()
    }

}