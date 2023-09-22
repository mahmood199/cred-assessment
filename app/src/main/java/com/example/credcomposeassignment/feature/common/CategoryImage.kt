package com.example.credcomposeassignment.feature.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.credcomposeassignment.data.models.CategoryProperty
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CategoryImage(
    categoryProperty: CategoryProperty,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        GlideImage(
            imageModel = {
                categoryProperty.iconUrl
            },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds
            ),
            modifier = Modifier.size(100.dp)
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .size(24.dp)
            )
        }
    }
}
