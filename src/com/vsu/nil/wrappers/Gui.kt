/**
 * Created by venevitinnikita@gmail.com on 25.04.16.
 */
package com.vsu.nil.wrappers

import java.awt.Component
import java.awt.Container
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel


fun window(func: JFrame.(JFrame) -> Unit = {}): JFrame {
    val window = JFrame()
    window.func(window)
    window.setVisible(true)
    return window
}

fun panel(func: JPanel.(JPanel) -> Unit = {}) = addChild(JPanel(), func)
fun label(func: JLabel.(JLabel) -> Unit = {}) = addChild(JLabel(), func)
fun button(func: JButton.(JButton) -> Unit = {}) = addChild(JButton(), func)

fun <C : Component> addChild(child: C, func: C.(C) -> Unit = {}): C {
    child.func(child)
    return child
}

val CENTER = JLabel.CENTER
val MAXIMIZED_BOTH = JFrame.MAXIMIZED_BOTH

infix fun <C : Component> C.to(container: Container): C {
    container.add(this)
    return this
}