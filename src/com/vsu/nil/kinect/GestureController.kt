package com.vsu.nil.kinect

import com.primesense.NITE.HandEventArgs
import com.primesense.NITE.HandPointContext
import com.primesense.NITE.IdEventArgs
import com.primesense.NITE.NullEventArgs
import com.vsu.nil.widgets.HandPanel
import org.OpenNI.EventArgs
import java.awt.Point
import java.util.*

/**
 * Created by Thor on 04.05.2016.
 */

val gController = object: GestureController() {}

abstract class GestureController {
//    private var hands: Set<Point>
    val handPanel = HandPanel()
    val kinectSensor = KinectSensor(this)

    fun updateHand(hand: HandPointContext) {
        handPanel.updateHand(hand)
//        handPanel.repaint()
    }
    fun deleteHand(id: Int) {
        handPanel.deleteHand(id)
//        handPanel.repaint()
    }
}