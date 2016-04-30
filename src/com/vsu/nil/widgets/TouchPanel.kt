/**
 * Created by venevitinnikita@gmail.com on 29.04.16.
 */
package com.vsu.nil.widgets

import com.vsu.nil.wrappers.addChild
import java.awt.Color
import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import javax.swing.JFrame
import javax.swing.JPanel


fun JFrame.touchPanel(func: TouchPanel.() -> Unit = {}) = addChild(TouchPanel(), func)


const val INITIAL_WIDTH = 100
const val INITIAL_HEIGHT = 100

class TouchPanel : JPanel {
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
    private var normalSize = Dimension(normalWidth, normalHeight)

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
    private var activatedSize = Dimension(activatedWidth, activatedHeight)

    var normalColor = Color.GRAY
        set(value) {
            field = value
            if (!activated) background = normalColor
        }
    var activatedColor = Color.GREEN
        set(value) {
            field = value
            if (activated) background = activatedColor
        }

    constructor() : super() {
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

    var activated: Boolean = false
        set(value) {
            if (value xor field) {
                background = if (value) {
                    size = activatedSize
                    activatedColor
                } else {
                    size = normalSize
                    normalColor
                }
                field = value
            }
        }
}