/**
 * Created by venevitinnikita@gmail.com on 25.04.16.
 */
package com.vsu.nil.wrappers

import com.vsu.nil.kinect.KinectWindowListener
import com.vsu.nil.kinect.gController
import java.awt.Component
import java.awt.Container
import javax.swing.*


@JvmOverloads fun window(func: JFrame.(JFrame) -> Unit = {}): JFrame {
    val window = JFrame()
    window.addWindowListener(KinectWindowListener())
    window.setSize(800, 600)
    window.func(window)
    window.setVisible(true)
    return window
}
fun niWindow(func: JFrame.(JFrame) -> Unit = {}): JFrame {
    val niWindow = gController.niFrame
    niWindow.setSize(800, 600)
    niWindow.func(niWindow)
    niWindow.setVisible(true)
    return niWindow
}

@JvmOverloads fun panel(func: JPanel.(JPanel) -> Unit = {}) = addChild(JPanel(), func)
@JvmOverloads fun label(func: JLabel.(JLabel) -> Unit = {}) = addChild(JLabel(), func)
@JvmOverloads fun button(func: JButton.(JButton) -> Unit = {}) = addChild(JButton(), func)
//fun handPanel(func: JPanel.(JPanel) -> Unit = {}) = addChild(gController.handPanel, func)
fun component(func: JComponent.(JComponent) -> Unit = {}) = addChild(object: JComponent(){}, func)

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