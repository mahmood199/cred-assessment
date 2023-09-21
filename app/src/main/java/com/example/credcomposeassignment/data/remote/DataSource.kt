package com.example.credcomposeassignment.data.remote

import com.example.credcomposeassignment.data.models.SectionResponse

interface DataSource {

    suspend fun getCategories() : SectionResponse

}