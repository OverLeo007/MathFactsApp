package ru.paskal.mathfacts.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoudalim.compose_rating_bar.RatingBarView
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.ui.theme.AppTypography
import ru.paskal.mathfacts.utils.getColor


@Composable
fun CardImpl(
    text: String,
    buttonOnClick: () -> Unit,
    isLoading: Boolean = false,
    rating: MutableState<Int> = remember {
        mutableIntStateOf(0)
    }
) {

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
                if (isLoading) {
                    CircularProgressIndicator(
                        color = getColor(id = R.color.black)
                    )
                } else {
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
}