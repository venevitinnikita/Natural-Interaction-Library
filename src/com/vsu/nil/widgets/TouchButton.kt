/**
 * Created by venevitinnikita@gmail.com on 05.05.16.
 */
package com.vsu.nil.widgets

import java.awt.Dimension
import java.awt.Graphics
import java.util.*
import javax.swing.JComponent
import kotlin.concurrent.timer


fun TouchPanel.button(func: TouchButton.() -> Unit = {}) = addTouchableChild(TouchButton(), func)

class TouchButton : JComponent(), TouchableContent, KClickable {
    override val kClickHandlers: MutableList<() -> Unit> = ArrayList()

    override var activated: Boolean = false
        set(value) {
            size = if (value) parentActivatedSize else parentNormalSize
            field = value
        }
    override var parentActivatedSize = Dimension()
    override var parentNormalSize = Dimension()

    var isClicked = false

    override fun click() {
        super.click()

        isClicked = true
        repaint()
        timer(initialDelay = 1000, period = 666) {
            isClicked = false
            repaint()
            cancel()
        }
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.drawLine(0, 0, width, height)
        if (isClicked) g?.drawLine(0, height, width, 0)
    }
}