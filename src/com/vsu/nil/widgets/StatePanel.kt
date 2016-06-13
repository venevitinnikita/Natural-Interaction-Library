package com.vsu.nil.widgets

import com.primesense.NITE.HandPointContext
import java.awt.*
import javax.swing.*

/**
 * Created by Thor on 03.05.2016.
 */

class StatePanel(hands: MutableMap<Int, Point>?) : JComponent() {
    private val hands: MutableMap<Int, Point> = hands ?: mutableMapOf()
    private val pDiam = 20
    //иконка статуса
    private val trackingStatusView = TrackingStatusView()
    private val calibrationStatusView = JLabel("Калибровка завершена!")

    init {
        trackingStatusView to this

        calibrationStatusView.isVisible = false
        calibrationStatusView to this

//        bounds = Rectangle(0, 0, 800, 600)
        background = Color.CYAN
    }

    constructor(): this(null)

    override fun paintComponent(g: Graphics) {
        println("repainting handPanel: ${hands.size} hands")
        super.paintComponent(g)
        //drawing hands' points
        val color = g.color
        g.color = Color.black
        for (hand in hands) {
            val point = hand.value
            g.fillOval(point.x-pDiam/2, point.y-pDiam/2, pDiam, pDiam)
            //            g.drawOval(point.x-pDiam/2, point.y-pDiam/2, pDiam, pDiam)

        }
        g.color = color
    }

    fun updateHand(hand: HandPointContext) {
        val position = hand.position
        println("updating ${hand.id} hand: (${position.x.toInt()}, ${position.y.toInt()})")
        hands.put(hand.id, Point(position.x.toInt(), position.y.toInt()))
        parent.repaint()
        //        revalidate()
    }
    fun deleteHand(id: Int) {
        hands.remove(id)
        parent.repaint()
        //        revalidate()
    }

    fun calibration(time: Int) {
        if (time > 0) {
            calibrationStatusView.isVisible = true
            calibrationStatusView.text = "калибровка ${time} сек..."
            parent.repaint()
        } else {
            calibrationStatusView.isVisible = false
            parent.repaint()
        }
    }
}

//private class StatusPanel(hands: MutableMap<Int, Point>?) : JComponent() {
//
//    private val hands: MutableMap<Int, Point> = hands ?: mutableMapOf()
//    private val pDiam = 20
//    private val trackingStatusView = TrackingStatusView()
//    private val calibrationStatusView = JLabel("Калибровка завершена!")
//
//    init {
//        trackingStatusView to this
//
//        calibrationStatusView.isVisible = false
//        calibrationStatusView to this
//    }
//
//    override fun paintComponent(g: Graphics) {
//        println("debug: repainting handPanel: ${hands.size} hands")
//        super.paintComponent(g)
//        //drawing hands' points
//        val color = g.color
//        g.color = Color.black
//        for (hand in hands) {
//            val point = hand.value
//            g.fillOval(point.x-pDiam/2, point.y-pDiam/2, pDiam, pDiam)
//            //            g.drawOval(point.x-pDiam/2, point.y-pDiam/2, pDiam, pDiam)
//
//        }
//        g.color = color
//    }
//
//    fun updateHand(hand: HandPointContext) {
//        val position = hand.position
//        println("debug: updating ${hand.id} hand: (${position.x.toInt()}, ${position.y.toInt()})")
//        hands.put(hand.id, Point(position.x.toInt(), position.y.toInt()))
//        parent.repaint()
//        //        revalidate()
//    }
//    fun deleteHand(id: Int) {
//        hands.remove(id)
//        parent.repaint()
//        //        revalidate()
//    }
//
//    fun calibration(time: Int?) {
//        if (time != null) {
//            calibrationStatusView.isVisible = true
//            for (t in time..1) {
//                calibrationStatusView.text = "калибровка ${t} сек..."
//                parent.repaint()
//            }
//        } else {
//            calibrationStatusView.isVisible = false
//            parent.repaint()
//        }
//    }
//}