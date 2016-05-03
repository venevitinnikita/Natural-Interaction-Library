/**
 * Created by venevitinnikita@gmail.com on 03.05.16.
 */
package com.vsu.nil.kinect

import com.vsu.nil.widgets.TouchableContainer
import java.awt.Point
import java.util.*
import kotlin.concurrent.timer


private val trackedPanels = HashSet<TouchableContainer>()

fun trackPanel(panel: TouchableContainer) {
    trackedPanels.add(panel)
}

class HandTracker {

    val timer: Timer = Timer()

    /**
     * Координаты отслеживаемой руки.
     * null, если рука не отслеживается
     */
    var position: Point? = null

    fun updatePanels() {
        val pos = position
        pos?.let {
            for (panel in trackedPanels) {
                if (panel.activated) {
                    if (!panel.isTouched(pos)) {
                        var count = 0
                        timer(initialDelay = 250, period = 250) {
                            val newPos = position
                            pos?.let {
                                count++
                                if (panel.isTouched(pos)) cancel()
                                if (count++ >= 2) panel.activated = false
                            } ?: cancel()
                        }
                    }
                } else {
                    if (panel.isTouched(pos)) {
                        var count = 0
                        timer(initialDelay = 250, period = 250) {
                            val newPos = position
                            pos?.let {
                                count++
                                if (!panel.isTouched(pos)) cancel()
                                if (count++ >= 2) panel.activated = true
                            } ?: cancel()
                        }
                    }
                }
            }
        }
    }
}