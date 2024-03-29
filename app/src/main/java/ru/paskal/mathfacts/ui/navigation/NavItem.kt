package ru.paskal.mathfacts.ui.navigation

import ru.paskal.mathfacts.R

sealed class NavItem(val title: String, val iconId: Int, val route: String) {
    data object CoordsFacts: NavItem("Coords Fact", R.drawable.coords_icon, Routes.CoordsScreenRoute)
    data object DateFacts: NavItem("Date Facts", R.drawable.date_icon, Routes.DateScreenRoute)
    data object RegularFacts: NavItem("Regular Facts", R.drawable.regular_icon, Routes.RegularScreenRoute)
    data object MathFacts: NavItem("Math Facts", R.drawable.math_icon, Routes.MathScreenRoute)

}

val listItems = listOf(
    NavItem.CoordsFacts,
    NavItem.DateFacts,
    NavItem.RegularFacts,
    NavItem.MathFacts,
)

