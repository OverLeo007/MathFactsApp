package ru.paskal.mathfacts.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.models.MathFact
import ru.paskal.mathfacts.ui.theme.AppTypography
import ru.paskal.mathfacts.utils.getColor

@Composable
fun SavedListCard(fact: String) {
    /* TODO Сделать точку входа для событий элемента и его наполнения */

    val context = LocalContext.current
    val openModal = remember { mutableStateOf(false) }
    val modalRating = remember {
        mutableIntStateOf(0)
    }
    val modalText = remember {
        mutableStateOf("")
    }
    val modalId = remember {
        mutableIntStateOf(0)
    }
    when {
        openModal.value -> {
            FactModal(
                onSaveRequest = {
                    openModal.value = false
                    Toast
                        .makeText(context, "Save ${modalId.intValue} from modal", Toast.LENGTH_SHORT)
                        .show()
                },
                onDeleteRequest = {
                    openModal.value = false
                    Toast
                        .makeText(context, "Delete ${modalId.intValue} from modal", Toast.LENGTH_SHORT)
                        .show()
                },
                onDismissRequest = {
                    openModal.value = false
                },
                rating = modalRating,
                text = modalText
            )

        }
    }
    Card(
        modifier = Modifier.height(500.dp),
        colors = CardDefaults.cardColors(
            containerColor = getColor(id = R.color.card_bga)
        )
    ) {
        Column {
            SortingDropdown()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp),
            ) {
                items(56) {
                    val fact = MathFact(
                        rating = 4,
                        factText = "Fact $it: $fact",
                    )
                    ListElement(
                        index = it,
                        fact = fact,
                        onClick = {
                            openModal.value = true
                            modalRating.intValue = fact.rating
                            modalText.value = fact.factText
                            modalId.intValue = it
                            Toast
                                .makeText(context, "Открыт факт $it", Toast.LENGTH_SHORT)
                                .show()
                        },
                        onDeleteClick = {
                            Toast.makeText(context, "Удаление $it", Toast.LENGTH_SHORT).show()
                        },
                    )
                }
            }
        }
    }
}


@Composable
fun ListElement(index: Int, fact: MathFact, onClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.star_full),
                contentDescription = "Icon",
                modifier = Modifier.size(25.dp),
                tint = Color.Yellow
            )
            Text(fact.rating.toString())
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = fact.factText,
                maxLines = 2,
                modifier = Modifier.weight(1F),
                style = AppTypography.bodySmall.copy(lineHeight = 15.sp),
                overflow = TextOverflow.Ellipsis
            )
            IconButton(onClick = onDeleteClick) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(R.drawable.cross),
                    contentDescription = "Icon"
                )
            }
        }
    }
}