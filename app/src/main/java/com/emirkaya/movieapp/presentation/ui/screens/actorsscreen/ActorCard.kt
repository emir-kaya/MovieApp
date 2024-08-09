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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.actor.ActorItem
import com.emirkaya.movieapp.util.Constants
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ActorCard(actor: ActorItem, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable {

            }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                imageModel = buildImageUrl(actor.profilePath),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(40.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = actor.name ?: stringResource(R.string.unknown),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = actor.knownForDepartment ?: stringResource(R.string.unknown),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            when (actor.gender) {
                3 -> Icon(
                    painter = painterResource(id = R.drawable.ic_homosexual),
                    contentDescription = stringResource(R.string.homosexual),
                    tint = Color.Red,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                2 -> Icon(
                    painter = painterResource(id = R.drawable.ic_man),
                    contentDescription = stringResource(R.string.male),
                    tint = Color.Blue,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                1 -> Icon(
                    painter = painterResource(id = R.drawable.ic_woman),
                    contentDescription = stringResource(R.string.female),
                    tint = Color.Magenta,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
            }
        }
    }
}

fun buildImageUrl(posterPath: String?): String {
    return "${Constants.BASE_IMG_URL}$posterPath"
}
