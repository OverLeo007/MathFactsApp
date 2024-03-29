package ru.paskal.mathfacts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mahmoudalim.compose_rating_bar.RatingBarView
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.utils.getColor

@Composable
fun FactModal(
    onSaveRequest: () -> Unit,
    onDeleteRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    rating: MutableState<Int>,
    text: MutableState<String>
    ) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = getColor(id = R.color.card_bga)
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = text.value,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                    )
                    RatingBarView(
                        rating = rating,
                        isRatingEditable = true,
                        isViewAnimated = false,
                        starIcon = painterResource(id = R.drawable.star_border),
                        starsPadding = 16.dp,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            modifier = Modifier.weight(0.8F),
                            onClick = onSaveRequest,
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 4.dp,
                                pressedElevation = 0.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = getColor(id = R.color.button)
                            ),
                        ) {
                            Text(
                                text = "Сохранить",
                                color = getColor(id = R.color.font)
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1F))
                        Button(
                            modifier = Modifier.weight(0.8F),
                            onClick = onDeleteRequest,
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 4.dp,
                                pressedElevation = 0.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = getColor(id = R.color.button)
                            ),
                        ) {
                            Text(
                                text = "Удалить",
                                color = getColor(id = R.color.font)
                            )
                        }
                    }

                }
            }
        }
    }
}