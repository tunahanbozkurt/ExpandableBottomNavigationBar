package com.example.expandable_bottom_navigation_bar

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.example.expandable_bottom_navigation_bar.model.Menu
import com.example.expandable_bottom_navigation_bar.model.MenuParser
import com.example.expandable_bottom_navigation_bar.view.MenuItemView

class ExpandableBottomNavigationBar : ConstraintLayout {

    private var menuItemBackgroundShape: Int = -1
    private var listener: OnItemSelectedListener? = null
    private var menu: Menu? = null

    constructor(context: Context) : super(context) {
        init(attrs = null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs = attrs)
    }

    private fun init(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.ExpandableBottomNavigationBar, 0, 0
        ).apply {
            try {
                val menuResource = getResourceId(
                    R.styleable.ExpandableBottomNavigationBar_ebnb_menuResource,
                    -1
                )

                menuItemBackgroundShape = getResourceId(
                    R.styleable.ExpandableBottomNavigationBar_ebnb_itemBackgroundShape,
                    -1
                )

                if (menuResource >= 0) {
                    setMenuResource(menuResource)
                }

            } finally {
                recycle()
            }
        }
    }

    private fun setMenuResource(@MenuRes menuResource: Int) {
        removeAllViews()

        val menu = MenuParser(context).parse(menuResource)
        this.menu = menu

        val childListener: (View) -> Unit = { view -> setItemSelected(view.id) }

        menu.items.forEach { menuItem ->
            MenuItemView(context).apply {
                bind(menuItem)
                setShape(menuItemBackgroundShape)
                setOnClickListener(childListener)
                isSelected = false
            }.also(this::addView)
        }

        getFlow().apply {
            referencedIds = menu.items.map { it.id }.toIntArray()
        }.also(this::addView)
    }

    fun setItemSelected(id: Int, isSelected: Boolean = true, triggerListener: Boolean = true) {
        val selectedItem = getSelectedItem()

        when {
            isSelected && selectedItem?.id != id -> {
                selectedItem?.isSelected = false
                getItemById(id)?.let {
                    beginAnimation()
                    it.isSelected = true
                    if (triggerListener) listener?.onItemSelected(id)
                }
            }
            !isSelected -> {
                getItemById(id)?.let {
                    beginAnimation()
                    it.isSelected = false
                }
            }
        }
    }

    private fun getSelectedItem() = children.firstOrNull { it.isSelected }

    private fun getItemById(id: Int) = children
        .filterIsInstance<MenuItemView>()
        .firstOrNull { it.id == id }


    private fun getFlow() = Flow(context).apply {
        setOrientation(Flow.HORIZONTAL)
        setHorizontalStyle(Flow.CHAIN_SPREAD)
        setHorizontalAlign(Flow.HORIZONTAL_ALIGN_START)
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }


    private fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }


    private fun setOnItemSelectedListener(block: (Int) -> Unit) {
        setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                block(id)
            }
        })
    }

    private fun beginAnimation() {
        val transition = AutoTransition().apply {
            duration = ANIMATION_DURATION
        }
        TransitionManager.beginDelayedTransition(this, transition)
    }

    interface OnItemSelectedListener {
        fun onItemSelected(id: Int)
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState())
        bundle.putInt(SELECTED_ITEM_ID, getSelectedItem()?.id ?: -1)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            val selectedItemId = state.getInt(SELECTED_ITEM_ID, -1)
            val superState = state.getParcelable<Parcelable>(SUPER_STATE)
            if (selectedItemId != -1) setItemSelected(selectedItemId)
            super.onRestoreInstanceState(superState)
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    fun setupWithNavController(
        navController: NavController
    ) {

        setOnItemSelectedListener {
            navController.navigate(it, null, NavOptions.Builder().apply {
                setLaunchSingleTop(true)
                setRestoreState(true)
                setPopUpTo(
                    navController.graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )
            }.build())
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (menu?.items?.any { it.id == destination.id } == true) {
                setItemSelected(destination.id, triggerListener = false)
            }
        }
    }

    fun setupCustomNavigationWithOptions(navController: NavController, options: NavOptions) {

        setOnItemSelectedListener {
            navController.navigate(it, null, options)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (menu?.items?.any { it.id == destination.id } == true) {
                setItemSelected(destination.id, triggerListener = false)
            }
        }
    }

    companion object {
        const val ANIMATION_DURATION = 150L
        const val SUPER_STATE = "SUPER_STATE"
        const val SELECTED_ITEM_ID = "SELECTED_ITEM_ID"
    }
}