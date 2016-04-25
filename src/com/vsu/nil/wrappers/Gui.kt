/**
 * Created by nikita on 25.04.16.
 */
package com.vsu.nil.wrappers

import java.awt.Component
import java.awt.Container
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel


fun window(func: JFrame.() -> Unit = {}): JFrame {
    val window = JFrame()
    window.func()
    window.setVisible(true)
    return window
}

fun JFrame.panel(func: JPanel.() -> Unit = {}) = addChild(JPanel(), func)
fun JFrame.label(func: JLabel.() -> Unit = {}) = addChild(JLabel(), func)

fun JPanel.label(func: JLabel.() -> Unit = {}) = addChild(JLabel(), func)
fun JPanel.button(func: JButton.() -> Unit = {}) = addChild(JButton(), func)

fun <C : Component> Container.addChild(child: C, func: C.() -> Unit = {}): C {
    child.func()
    return child
}

val CENTER = JLabel.CENTER
val MAXIMIZED_BOTH = JFrame.MAXIMIZED_BOTH

infix fun Component.to(container: Container) {
    container.add(this)
}

infix fun JFrame.onClose(func: () -> Unit) {
    addWindowListener(object : WindowAdapter() {
        override fun windowClosing(windowEvent: WindowEvent) {
            func()
        }
    })
}