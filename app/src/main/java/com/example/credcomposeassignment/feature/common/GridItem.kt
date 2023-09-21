package com.example.credcomposeassignment.feature.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.example.credcomposeassignment.data.models.CategoryProperty
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GridItem(
    categoryProperty: CategoryProperty,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        GlideImage(
            imageModel = {
                categoryProperty.iconUrl
            },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds
            ),
            modifier = Modifier.size(80.dp)
        )
        Text(text = categoryProperty.name, textAlign = TextAlign.Center)
    }
}