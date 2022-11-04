package ru.liquidtoiletpaper.myapplication.screens.profileScreens

class MyPurchasesList() {
    companion object {
        var purchases = listOf(
            MyPurchasesItem(
                status = false,
                index = "1 111 111 111",
                date = "01/01/2001",
                value = 100
            ),
            MyPurchasesItem(
                status = true,
                index = "2 222 222 222",
                date = "02/02/2002",
                value = 200
            ),
            MyPurchasesItem(
                status = true,
                index = "3 333 333 333",
                date = "03/03/2003",
                value = 300
            ),
            MyPurchasesItem(
                status = false,
                index = "4 444 444 444",
                date = "04/04/2004",
                value = 400
            ),
            MyPurchasesItem(
                status = true,
                index = "5 555 555 555",
                date = "05/05/2005",
                value = 50000
            ),
        )
        /*
        fun getSelected(): String {
            for(i in CitiesList.cities){
                if (i.selected) {
                    return i.city
                }
            }
            return "Moscow"
        }
         */
    }
}