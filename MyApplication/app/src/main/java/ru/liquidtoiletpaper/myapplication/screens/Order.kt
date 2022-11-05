package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.Image
import ru.liquidtoiletpaper.myapplication.R

class Order() {
    val products = listOf(
        ProductItem(
            id = "1",
            image = R.drawable.rtx4090,
            name = "ROG Strix GeForce RTX 4090 24GB",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sagittis mi ac diam tempor pretium. Sed ut nibh in velit.",
            cost = 4000
        ),
        ProductItem(
            id = "2",
            image = R.drawable.ryzen9_5900x,
            name = "AMD Ryzen 9 5900X",
            description = "Sed ut nibh in velit.",
            cost = 550
        ),
        ProductItem(
            id = "3",
            image = R.drawable.cybercore_1000w_platinum_cybercore1000_bkceu,
            name = "Блок питания XPG CYBERCORE 1000W [CYBERCORE1000P-BKCEU]",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sagittis mi ac diam tempor pretium. Sed ut nibh in velit.",
            cost = 350
        ),
    )
}