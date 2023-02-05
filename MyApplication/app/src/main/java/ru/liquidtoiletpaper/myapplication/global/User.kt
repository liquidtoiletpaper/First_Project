package ru.liquidtoiletpaper.myapplication.global

import kotlinx.serialization.json.JsonElement

object User {
    var id = 0
    var email = ""
    var location = ""
    var name = ""
    var lastname = ""
    var gender = ""
    var password = ""
    var favProducts = JsonElement
    var cartProducts = JsonElement
}