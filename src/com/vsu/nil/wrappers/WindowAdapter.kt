/**
 * Created by venevitinnikita@gmail.com on 29.04.16.
 */
package com.vsu.nil.wrappers

import java.awt.event.WindowEvent
import java.awt.event.WindowFocusListener
import java.awt.event.WindowListener
import java.awt.event.WindowStateListener
import java.util.*
import javax.swing.JFrame


private val windowAdapters by lazy { HashMap<JFrame, WindowAdapter>() }

val JFrame.events: WindowAdapter
    get() {
        var adapter = windowAdapters[this]
        if (adapter === null) {
            adapter = WindowAdapter()
            windowAdapters[this] = adapter

            addWindowStateListener(adapter)
            addWindowFocusListener(adapter)
            addWindowListener(adapter)
        }
        return adapter
    }


class WindowAdapter : WindowListener, WindowStateListener, WindowFocusListener {

    operator fun invoke(func: WindowAdapter.() -> Unit) {
        this.func()
    }

    var onDeiconified: (e: WindowEvent?) -> Unit = {}
    override fun windowDeiconified(e: WindowEvent?) {
        onDeiconified(e)
    }

    var onActivated: (e: WindowEvent?) -> Unit = {}
    override fun windowActivated(e: WindowEvent?) {
        onActivated(e)
    }

    var onDeactivated: (e: WindowEvent?) -> Unit = {}
    override fun windowDeactivated(e: WindowEvent?) {
        onDeactivated(e)
    }

    var onIconofied: (e: WindowEvent?) -> Unit = {}
    override fun windowIconified(e: WindowEvent?) {
        onIconofied(e)
    }

    var onClosing: (e: WindowEvent?) -> Unit = {}
    override fun windowClosing(e: WindowEvent?) {
        onClosing(e)
    }

    var onClosed: (e: WindowEvent?) -> Unit = {}
    override fun windowClosed(e: WindowEvent?) {
        onClosed(e)
    }

    var onOpened: (e: WindowEvent?) -> Unit = {}
    override fun windowOpened(e: WindowEvent?) {
        onOpened(e)
    }

    var onStateChanged: (e: WindowEvent?) -> Unit = {}
    override fun windowStateChanged(e: WindowEvent?) {
        onStateChanged(e)
    }

    var onLostFocus: (e: WindowEvent?) -> Unit = {}
    override fun windowLostFocus(e: WindowEvent?) {
        onLostFocus(e)
    }

    var onGainedFocus: (e: WindowEvent?) -> Unit = {}
    override fun windowGainedFocus(e: WindowEvent?) {
        onGainedFocus(e)
    }
}