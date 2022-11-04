package ru.liquidtoiletpaper.myapplication.screens.profileScreens

class CitiesList() {
    companion object {
        var cities = mutableListOf(
            CitiesItem(
                city = "Yakutsk",
                selected = true
            ),
            CitiesItem(
                city = "Moscow",
                selected = false
            ),
            CitiesItem(
                city = "Saint Petersburg",
                selected = false
            ),
            CitiesItem(
                city = "Novosibirsk",
                selected = false
            ),
            CitiesItem(
                city = "Nizhniy Novgorod",
                selected = false
            )
        )
        fun getSelected(): String {
            for(i in cities){
                if (i.selected) {
                    return i.city
                }
            }
            return "Moscow"
        }
    }
}