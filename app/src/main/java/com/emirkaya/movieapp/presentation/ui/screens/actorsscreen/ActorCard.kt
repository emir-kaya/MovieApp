package com.emirkaya.movieapp.presentation.ui.screens.actorsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.actor.ActorItem
import com.emirkaya.movieapp.presentation.ui.theme.ActorCardDimensions
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.util.ImageUtil.buildImageUrl
import com.skydoves.landscapist.glide.GlideImage

enum class Gender {
    FEMALE,
    MALE,
    GAY;

    companion object {
        fun fromId(id: Int): Gender {
            return when (id) {
                1 -> FEMALE
                2 -> MALE
                3 -> GAY
                else -> GAY
            }
        }
    }
}

@Composable
fun ActorCard(actor: ActorItem, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(ActorCardDimensions.cornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = ActorCardDimensions.elevation),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onSecondary),
        modifier = Modifier
            .padding(ActorCardDimensions.padding)
            .fillMaxWidth()
            .height(ActorCardDimensions.height)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(ActorCardDimensions.padding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                imageModel = buildImageUrl(actor.profilePath),
                contentDescription = null,
                modifier = Modifier
                    .size(ActorCardDimensions.imageSize)
                    .clip(RoundedCornerShape(ActorCardDimensions.imageSize / 2))
            )
            Spacer(modifier = Modifier.width(ActorCardDimensions.spacerWidth))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = actor.name ?: stringResource(R.string.unknown),
                    fontWeight = FontWeight.Bold,
                    fontSize = ActorCardDimensions.nameFontSize,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(ActorCardDimensions.smallSpacerWidth))
                Text(
                    text = actor.knownForDepartment ?: stringResource(R.string.unknown),
                    fontSize = ActorCardDimensions.departmentFontSize,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(ActorCardDimensions.smallSpacerWidth))

            val gender = when (Gender.fromId(actor.gender ?: 0)) {
                Gender.FEMALE -> {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_woman),
                        contentDescription = stringResource(R.string.female),
                        tint = Color.Magenta,
                        modifier = Modifier
                            .size(ActorCardDimensions.iconSize)
                            .padding(end = ActorCardDimensions.smallSpacerWidth)
                    )
                }
                Gender.MALE -> {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_man),
                        contentDescription = stringResource(R.string.male),
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(ActorCardDimensions.iconSize)
                            .padding(end = ActorCardDimensions.smallSpacerWidth)
                    )
                }
                Gender.GAY -> {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_homosexual),
                        contentDescription = stringResource(R.string.homosexual),
                        tint = Color.Red,
                        modifier = Modifier
                            .size(ActorCardDimensions.iconSize)
                            .padding(end = ActorCardDimensions.smallSpacerWidth)
                    )
                }
            }
        }
    }
}



