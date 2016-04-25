/**
 * Created by nikita on 24.04.16.
 */
package com.vsu.nil

import com.vsu.nil.wrappers.*
import java.awt.FlowLayout
import java.awt.GridLayout

fun main(args: Array<String>) {
    window {
        layout = GridLayout(3, 1)
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
    } onClose { System.exit(0) }
}