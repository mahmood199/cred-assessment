package com.example.credcomposeassignment.data.remote

import com.example.credcomposeassignment.data.models.SectionResponse

interface SectionService {

    suspend fun getSections() : SectionResponse

}