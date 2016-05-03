/**
 * Created by venevitinnikita@gmail.com on 29.04.16.
 */
package com.vsu.nil.widgets

import com.vsu.nil.kinect.trackPanel
import com.vsu.nil.wrappers.addChild
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.LineBorder


fun JFrame.touchPanel(func: TouchPanel.() -> Unit = {}): TouchPanel {
    val panel = TouchPanel()
    trackPanel(panel)
    return addChild(panel, func)
}

fun TouchPanel.content(func: TouchContent.() -> Unit = {}): TouchContent {
    val child = TouchContent()
    touchableChildren.add(child)
    child.parentNormalSize = normalSize
    child.parentActivatedSize = activatedSize
    child.func()

    add(child, BorderLayout.CENTER)

    return child
}

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

class TouchContent : JPanel(), TouchableContent {
    override var activated: Boolean = false
        set(value) {
            size = if (value) parentActivatedSize else parentNormalSize
            field = value
        }
    override var parentActivatedSize: Dimension = Dimension()
    override var parentNormalSize: Dimension = Dimension()
}

const val INITIAL_WIDTH = 100
const val INITIAL_HEIGHT = 100

class TouchPanel : JPanel, TouchableContainer {
    override val touchableChildren = ArrayList<TouchableContent>()

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

    constructor() : super(BorderLayout()) {
        addMouseMotionListener(object : MouseMotionListener {
            override fun mouseMoved(e: MouseEvent?) {
                activated = true
            }

            override fun mouseDragged(e: MouseEvent?) {
            }
        })
        addMouseListener(object : MouseListener {
            override fun mouseExited(e: MouseEvent?) {
                activated = false
            }

            override fun mouseClicked(e: MouseEvent?) {
            }

            override fun mouseEntered(e: MouseEvent?) {
            }

            override fun mousePressed(e: MouseEvent?) {
            }

            override fun mouseReleased(e: MouseEvent?) {
            }
        })
    }

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