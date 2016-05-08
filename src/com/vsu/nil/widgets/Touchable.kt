/**
 * Created by venevitinnikita@gmail.com on 05.05.16.
 */
package com.vsu.nil.widgets

import java.awt.Dimension
import java.awt.Point


interface Touchable {
    var activated: Boolean
}

interface TouchableContainer : Touchable {
    val touchableChildren: MutableList<TouchableContent>
    var normalSize: Dimension
    var activatedSize: Dimension

    fun isTouched(point: Point): Boolean
}

interface TouchableContent : Touchable {
    var parentNormalSize: Dimension
    var parentActivatedSize: Dimension
}

interface KClickable {
    val kClickHandlers: MutableList<() -> Unit>
    fun click() {
        kClickHandlers.forEach { it() }
    }

    fun onKClicked(func: () -> Unit) {
        kClickHandlers.add(func)
    }
}