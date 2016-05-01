/**
 * Created by venevitinnikita@gmail.com on 24.04.16.
 */
package com.vsu.nil

import com.vsu.nil.widgets.content
import com.vsu.nil.widgets.touchPanel
import com.vsu.nil.wrappers.*
import java.awt.Color
import java.awt.FlowLayout
import java.awt.GridLayout

fun main(args: Array<String>) {
    window {
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

            touchPanel {
                normalWidth = 100
                normalHeight = 100

                content {
                    background = Color.BLACK
                }
            } to this
        } to this

        events {
            onClosing = { System.exit(0) }
        }
    }
}