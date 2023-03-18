package com.example.expandable_bottom_navigation_bar.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.content.res.XmlResourceParser
import android.util.AttributeSet
import android.util.Xml
import androidx.annotation.MenuRes
import com.example.expandable_bottom_navigation_bar.R
import com.example.expandable_bottom_navigation_bar.util.getValueFromAttr
import org.xmlpull.v1.XmlPullParser.*

internal class MenuParser(private val context: Context) {

    private companion object {
        const val XML_MENU_TAG = "menu"
        const val XML_MENU_ITEM_TAG = "item"
    }

    fun parse(@MenuRes menuRes: Int): Menu {
        @SuppressLint("ResourceType")
        val parser = context.resources.getLayout(menuRes)
        val attrs = Xml.asAttributeSet(parser)

        skipMenuTagStart(parser)

        return parseMenu(parser, attrs)
    }

    private fun skipMenuTagStart(parser: XmlResourceParser) {
        var currentEvent = parser.eventType
        do {
            if (currentEvent == START_TAG) {
                val name = parser.name
                require(name == XML_MENU_TAG) { "Expecting menu, got $name" }
                break
            }
            currentEvent = parser.next()
        } while (currentEvent != END_DOCUMENT)
    }

    private fun parseMenu(
        parser: XmlResourceParser,
        attrs: AttributeSet
    ): Menu {
        val items = mutableListOf<MenuItem>()
        var eventType = parser.eventType
        var isEndOfMenu = false

        while (!isEndOfMenu) {
            val name = parser.name
            when {
                eventType == START_TAG && name == XML_MENU_ITEM_TAG -> {
                    val item = parseMenuItem(attrs)
                    items.add(item)
                }
                eventType == END_TAG && name == XML_MENU_TAG -> isEndOfMenu = true
                eventType == END_DOCUMENT -> throw RuntimeException("Unexpected end of document")

            }
            eventType = parser.next()
        }
        return Menu(items = items)
    }

    private fun parseMenuItem(attrs: AttributeSet): MenuItem {
        val sAttr = context.obtainStyledAttributes(attrs, R.styleable.ExpandableMenuItem)

        val item = MenuItem(
            id = sAttr.getResourceId(R.styleable.ExpandableMenuItem_android_id, 0),
            title = sAttr.getText(R.styleable.ExpandableMenuItem_android_title),
            contentDescription = sAttr.getText(R.styleable.ExpandableMenuItem_android_contentDescription),
            icon = sAttr.getResourceId(R.styleable.ExpandableMenuItem_android_icon, 0),
            selectedIconColor = readSelectedIconColor(sAttr),
            unSelectedIconColor = readUnSelectedIconColor(sAttr),
            selectedTextColor = readSelectedTextColor(sAttr),
            unSelectedTextColor = readUnSelectedTextColor(sAttr)
        )

        sAttr.recycle()
        return item
    }

    private fun readSelectedIconColor(sAttr: TypedArray): Int = sAttr.getColor(
        R.styleable.ExpandableMenuItem_ebnb_selectedIconColor,
        context.getValueFromAttr(androidx.appcompat.R.attr.colorAccent)
    )

    private fun readUnSelectedIconColor(sAttr: TypedArray): Int = sAttr.getColor(
        R.styleable.ExpandableMenuItem_ebnb_unSelectedIconColor,
        context.getValueFromAttr(androidx.appcompat.R.attr.colorAccent)
    )

    private fun readSelectedTextColor(sAttr: TypedArray): Int =
        sAttr.getColor(
            R.styleable.ExpandableMenuItem_ebnb_selectedTextColor,
            readSelectedIconColor(sAttr)
        )

    private fun readUnSelectedTextColor(sAttr: TypedArray): Int =
        sAttr.getColor(
            R.styleable.ExpandableMenuItem_ebnb_unSelectedTextColor,
            readUnSelectedIconColor(sAttr)
        )
}