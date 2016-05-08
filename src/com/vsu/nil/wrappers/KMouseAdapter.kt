/**
 * Created by venevitinnikita@gmail.com on 06.05.16.
 */
package com.vsu.nil.wrappers

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener


class KMouseAdapter : MouseMotionListener, MouseListener {

    operator fun invoke(func: KMouseAdapter.() -> Unit) {
        this.func()
    }

    var onMouseEntered: (e: MouseEvent?) -> Unit = {}
    override fun mouseEntered(e: MouseEvent?) {
        onMouseEntered(e)
    }

    var onMouseClicked: (e: MouseEvent?) -> Unit = {}
    override fun mouseClicked(e: MouseEvent?) {
        onMouseClicked(e)
    }

    var onMouseReleased: (e: MouseEvent?) -> Unit = {}
    override fun mouseReleased(e: MouseEvent?) {
        onMouseReleased(e)
    }

    var onMouseExited: (e: MouseEvent?) -> Unit = {}
    override fun mouseExited(e: MouseEvent?) {
        onMouseExited(e)
    }

    var onMousePressed: (e: MouseEvent?) -> Unit = {}
    override fun mousePressed(e: MouseEvent?) {
        onMousePressed(e)
    }

    var onMouseMoved: (e: MouseEvent?) -> Unit = {}
    override fun mouseMoved(e: MouseEvent?) {
        onMouseMoved(e)
    }

    var onMouseDragged: (e: MouseEvent?) -> Unit = {}
    override fun mouseDragged(e: MouseEvent?) {
        onMouseDragged(e)
    }

}