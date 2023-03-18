package com.example.expandable_bottom_navigation_bar.model

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

internal data class MenuItem(
    val id: Int,
    val title: CharSequence,
    val contentDescription: CharSequence?,
    @DrawableRes val icon: Int,
    @ColorInt val selectedIconColor: Int,
    @ColorInt val selectedTextColor: Int,
    @ColorInt val unSelectedIconColor: Int,
    @ColorInt val unSelectedTextColor: Int
)