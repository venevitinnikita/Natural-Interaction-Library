/**
 * Created by venevitinnikita@gmail.com on 29.04.16.
 */
package com.vsu.nil.widgets

import com.vsu.nil.kinect.trackPanel
import com.vsu.nil.wrappers.KMouseAdapter
import com.vsu.nil.wrappers.addChild
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import java.util.*
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.border.LineBorder


fun touchPanel(func: TouchPanel.(TouchPanel) -> Unit = {}): TouchPanel {
    val panel = TouchPanel()
    trackPanel(panel)
    return addChild(panel, func)
}

fun <T> TouchPanel.addTouchableChild(child: T, func: T.() -> Unit): T
        where T : JComponent, T : TouchableContent {
    touchableChildren.add(child)
    child.parentNormalSize = normalSize
    child.parentActivatedSize = activatedSize
    child.func()

    return child
}

const val INITIAL_WIDTH = 100
const val INITIAL_HEIGHT = 100

class TouchPanel : JPanel, TouchableContainer {
    override val touchableChildren by lazy { ArrayList<TouchableContent>() }
    val events = KMouseAdapter()

    var normalWidth = INITIAL_WIDTH
        set(value) {
            field = value
            normalSize.width = value
            if (!activated) size = normalSize
        }
    var normalHeight = INITIAL_HEIGHT
        set(value) {
            field = value
            normalSize.height = value
            if (!activated) size = normalSize
        }
    override var normalSize = Dimension(normalWidth, normalHeight)

    var activatedWidth = (normalWidth * 1.5).toInt()
        set(value) {
            field = value
            activatedSize.width = value
            if (activated) size = activatedSize
        }
    var activatedHeight = (normalHeight * 1.5).toInt()
        set(value) {
            field = value
            activatedSize.height = value
            if (activated) size = activatedSize
        }
    override var activatedSize = Dimension(activatedWidth, activatedHeight)

    var borderColor = Color.GREEN

    override var activated: Boolean = false
        set(value) {
            if (value xor field) {
                border = if (value) {
                    size = activatedSize
                    LineBorder(borderColor, 3)
                } else {
                    size = normalSize
                    null
                }
                touchableChildren.forEach { it.activated = value }
                field = value
            }
        }

    constructor() : super(BorderLayout()) {
        addMouseListener(events)
        addMouseMotionListener(events)
    }

    override fun isTouched(point: Point): Boolean {
        val width: Int
        val height: Int
        if (activated) {
            width = activatedWidth
            height = activatedHeight
        } else {
            width = normalWidth
            height = normalHeight
        }
        return x <= point.x && point.x <= x + width && y <= point.y && point.y <= y + height
    }
}


// TODO возможно это уже не понадобится
fun TouchPanel.content(func: TouchContent.() -> Unit = {}) {
    val child = TouchContent()
    addTouchableChild(child, func)
    add(child)
}

class TouchContent : JPanel(), TouchableContent {
    override var activated: Boolean = false
        set(value) {
            size = if (value) parentActivatedSize else parentNormalSize
            field = value
        }
    override var parentActivatedSize = Dimension()
    override var parentNormalSize = Dimension()
}