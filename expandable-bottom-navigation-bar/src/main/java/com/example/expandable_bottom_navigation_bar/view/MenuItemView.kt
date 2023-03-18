package com.example.expandable_bottom_navigation_bar.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.example.expandable_bottom_navigation_bar.R
import com.example.expandable_bottom_navigation_bar.model.MenuItem

internal class MenuItemView(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet) {

    private val title: TextView by lazy { findViewById(R.id.ebnb_item_title) }
    private val icon: ImageView by lazy { findViewById(R.id.ebnb_item_icon) }
    private val container: LinearLayout by lazy { findViewById(R.id.ebnb_item_container) }
    private var shape: Int = -1
    private var selectedColor: Int = -1
    private var unSelectedColor: Int = -1

    init {
        inflate(context, R.layout.menu_item, this)
    }

    fun setShape(@DrawableRes shape: Int) {
        this.shape = shape
    }

    private fun setSelectedColor(color: Int) {
        this.selectedColor = color
    }

    private fun setUnSelectedColor(color: Int) {
        this.unSelectedColor = color
    }

    fun bind(item: MenuItem) {
        id = item.id

        setSelectedColor(item.selectedIconColor)
        setUnSelectedColor(item.unSelectedIconColor)

        contentDescription = item.contentDescription

        title.text = item.title
        icon.setImageResource(item.icon)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if (selected) {
            title.visibility = View.VISIBLE
            if (shape != -1) {
                container.background = ResourcesCompat.getDrawable(
                    resources,
                    shape,
                    context.theme
                )
            }
            if (selectedColor != -1) {
                title.setTextColor(selectedColor)
                icon.setColorFilter(selectedColor)
            }
        } else {
            title.visibility = View.GONE
            container.background = null
            if (unSelectedColor != -1) {
                icon.setColorFilter(unSelectedColor)
            }
        }
    }
}