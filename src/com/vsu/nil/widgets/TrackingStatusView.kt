package com.vsu.nil.widgets

import com.vsu.nil.kinect.TrackingStatus
import com.vsu.nil.kinect.gController
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent

/**
 * Created by Thor on 11.06.2016.
 */

class TrackingStatusView : JComponent() {
    private val images: Map<TrackingStatus, BufferedImage>

    init {
        val offImg = ImageIO.read(File("resources/images/off.jpg"))
        val noUserImg = ImageIO.read(File("resources/images/no_user.jpg"))
        val calibrationImg = ImageIO.read(File("resources/images/calibration.jpg"))
        val trackingImg = ImageIO.read(File("resources/images/tracking.jpg"))

        images = mapOf(TrackingStatus.OFF to offImg,
                TrackingStatus.NO_USER to noUserImg,
                TrackingStatus.CALIBRATION to calibrationImg,
                TrackingStatus.TRACKING to trackingImg)
    }

    override fun paintComponent(g: Graphics) {
        g.drawImage(images.get(gController.status), 0, 0, null)
    }
}