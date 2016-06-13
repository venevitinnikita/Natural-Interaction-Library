/**
 * Created by venevitinnikita@gmail.com on 24.04.16.
 */
package com.vsu.nil

import com.vsu.nil.widgets.button
import com.vsu.nil.widgets.touchPanel
import com.vsu.nil.wrappers.*
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

fun main(args: Array<String>) {
    niWindow {
        panel {
            layout = GridLayout(4, 1)
            extendedState = MAXIMIZED_BOTH

            label {
                text = "Buttons"
                horizontalAlignment = CENTER
            } to this
            val status = label {
                horizontalAlignment = CENTER
            }
            panel {
                layout = FlowLayout()
                button {
                    text = "One"
                    addActionListener { status.text = text }
                } to this
                button {
                    text = "Two"
                    addActionListener { status.text = text }
                } to this
                button {
                    text = "Three"
                    addActionListener { status.text = text }
                } to this
            } to this
            status to this

            panel {
                layout = null

                touchPanel { touchPanel ->
                    normalWidth = 100
                    normalHeight = 100

                    events {
                        onMouseMoved = { activated = true }
                        onMouseExited = { activated = false }
                    }

                    val button = button { } to this
                    val mouseAdapter = object : MouseAdapter() {
                        override fun mouseClicked(e: MouseEvent?) {
                            button.click()
                        }

                        override fun mouseMoved(e: MouseEvent?) {
                            touchPanel.dispatchEvent(e)
                        }

                        override fun mouseExited(e: MouseEvent?) {
                            touchPanel.dispatchEvent(e)
                        }
                    }
                    button.addMouseListener(mouseAdapter)
                    button.addMouseMotionListener(mouseAdapter)
                } to this
            } to this
        } to this

        events {
            onClosing = { System.exit(0) }
        }
    }
}