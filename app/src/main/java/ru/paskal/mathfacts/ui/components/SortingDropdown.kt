package ru.paskal.mathfacts.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.paskal.mathfacts.R
import ru.paskal.mathfacts.utils.getColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingDropdown() {
    val context = LocalContext.current
    val variants = arrayOf("5 -> 1", "1 -> 5", "В порядке добавления")
    val starVariants = arrayOf("5 -> 1", "1 -> 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(variants[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.fillMaxWidth()
    ) {

        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            leadingIcon = {
                var icon = R.drawable.arrow_down
                if (selectedText in starVariants) {
                    icon = R.drawable.star
                }
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "Icon",
                    modifier = Modifier.size(20.dp),
                )
            },
            modifier = Modifier
                .menuAnchor()
//                .shadow(4.dp)
                .fillMaxWidth(),
//            textStyle = AppTypography.bodySmall,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = getColor(id = R.color.card_bga),
                focusedContainerColor = getColor(id = R.color.card_bga),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
        )

        ExposedDropdownMenu(
            modifier = Modifier
                .background(getColor(id = R.color.bga)),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            variants.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = {
                        Text(
                            text = item
                        )
                    },
                    onClick = {
                        selectedText = item
                        expanded = false
                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    },
                    leadingIcon = {
                        var icon = R.drawable.arrow_down
                        if (item in starVariants) {
                            icon = R.drawable.star
                        }
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = "Icon",
                            modifier = Modifier.size(20.dp),
                        )
                    },
                )
            }
        }
    }
}
