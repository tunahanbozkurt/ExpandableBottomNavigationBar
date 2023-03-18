package com.example.expandable_bottom_navigation_bar.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes

internal fun Context.getValueFromAttr(
    @AttrRes attr: Int
): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}