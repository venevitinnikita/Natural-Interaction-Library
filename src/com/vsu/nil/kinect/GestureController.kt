package com.vsu.nil.kinect

import com.primesense.NITE.HandPointContext
import com.vsu.nil.widgets.NIFrame

/**
 * Created by Thor on 04.05.2016.
 */

val gController = object: GestureController() {}

abstract class GestureController {
    var status = TrackingStatus.OFF
    val niFrame = NIFrame()
    val kinectSensor = KinectSensor(this)

    fun updateHand(hand: HandPointContext) {
        niFrame.updateHand(hand)
//        handPanel.repaint()
    }

    fun deleteHand(id: Int) {
        niFrame.deleteHand(id)
//        handPanel.repaint()
    }

    fun startCalibration(time: Int) {
        status = TrackingStatus.CALIBRATION
        for (t in time..1) {
            niFrame.calibration(t)
            Thread.sleep(1000)
        }
        status = TrackingStatus.TRACKING
    }
}

enum class TrackingStatus {
    OFF, NO_USER, CALIBRATION, TRACKING;
}