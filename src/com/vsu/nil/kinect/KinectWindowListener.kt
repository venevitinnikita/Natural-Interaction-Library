package com.vsu.nil.kinect

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

/**
 * Created by Thor on 07.05.2016.
 */

class KinectWindowListener: WindowAdapter() {
    override fun windowClosing(event: WindowEvent) {
        gController.kinectSensor.stop()
    }
}