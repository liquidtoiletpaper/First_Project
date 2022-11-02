package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Preview
@Composable
fun MyPurchasesItem() {
    //var status: Boolean
    val status = false
    //val index: String
    val index = "1 111 111 111"
    //val date: String
    val date = "01/01/2001"
    //val cost: Int
    val cost = 100
    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Black
                ),
                onClick = { },
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, top = 5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (!status) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Canceled",
                        tint = ErrorColor,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Accepted",
                        tint = PrimaryGreen,
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "№ $index",
                        textAlign = TextAlign.Start,
                        fontFamily = SemiBoldFont,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        color = PrimaryWhite,
                        letterSpacing = 1.sp,
                        modifier = Modifier
                            .weight(2f)
                    )
                    Text(
                        text = date,
                        fontFamily = NormalFont,
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp,
                        color = SecondaryText,
                        modifier = Modifier
                            .weight(1f),
                    )
                    Text(
                        text = "$cost $",
                        fontFamily = SemiBoldFont,
                        style = MaterialTheme.typography.h1,
                        fontSize = 14.sp,
                        color = PrimaryWhite,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 20.dp),
                        letterSpacing = 1.sp
                    )
                }
                if(!status) {
                    Text(
                        text = "Canceled",
                        textAlign = TextAlign.Start,
                        fontFamily = NormalFont,
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp,
                        color = DisabledText
                    )
                } else {
                    Text(
                        text = "Accepted",
                        textAlign = TextAlign.Start,
                        fontFamily = NormalFont,
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp,
                        color = DisabledText
                    )
                }
            }
        }
    }
}