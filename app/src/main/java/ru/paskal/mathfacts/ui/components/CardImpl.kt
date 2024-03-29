package ru.paskal.mathfacts.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoudalim.compose_rating_bar.RatingBarView
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.theme.AppTypography
import ru.paskal.mathfacts.utils.getColor


@Composable
fun CardImpl(text: String, buttonOnClick: () -> Unit) {
    val context = LocalContext.current
    val rating = remember {
        mutableIntStateOf(4)
    }

    LaunchedEffect(rating.intValue) {
        Toast.makeText(context, "stars: ${rating.intValue}", Toast.LENGTH_SHORT).show()
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = getColor(id = R.color.card_bga)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )

    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    lineHeight = 15.sp,
                    style = AppTypography.bodySmall
                )
                RatingBarView(
                    rating = rating,
                    isRatingEditable = true,
                    isViewAnimated = false,
                    starIcon = painterResource(id = R.drawable.star_border),
                    starsPadding = 16.dp,
                )
                ButtonImpl(
                    text = "Сохранить",
                    widthFraction = 0.8F,
                    onClickAction = buttonOnClick
                )
            }

        }
    }
}