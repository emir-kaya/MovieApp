package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.presentation.ui.theme.ActorDetailDimensions

@Composable
fun ActorDetailHeader(actorDetail: ActorDetailResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = ActorDetailDimensions.spacerHeightSmall),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = actorDetail.birthday ?: stringResource(R.string.unknown),
            fontSize = ActorDetailDimensions.fontSizeBiography,
            color = Color.Gray,
            modifier = Modifier.padding(end = ActorDetailDimensions.spacerHeightSmall)
        )

        Divider(
            color = Color.Gray, thickness = ActorDetailDimensions.dividerThickness, modifier = Modifier
                .height(ActorDetailDimensions.dividerHeight)
                .width(ActorDetailDimensions.dividerThickness)
        )

        Spacer(modifier = Modifier.width(ActorDetailDimensions.spacerHeightSmall))

        Text(
            text = actorDetail.placeOfBirth ?: stringResource(R.string.unknown),
            fontSize = ActorDetailDimensions.fontSizeBiography,
            color = Color.Gray
        )

        actorDetail.deathday?.let {
            Spacer(modifier = Modifier.width(ActorDetailDimensions.spacerHeightSmall))

            Divider(
                color = Color.Gray, thickness = ActorDetailDimensions.dividerThickness, modifier = Modifier
                    .height(ActorDetailDimensions.dividerHeight)
                    .width(ActorDetailDimensions.dividerThickness)
            )

            Spacer(modifier = Modifier.width(ActorDetailDimensions.spacerHeightSmall))

            Text(
                text = it.toString(),
                fontSize = ActorDetailDimensions.fontSizeBiography,
                color = Color.Gray
            )
        }
    }
}