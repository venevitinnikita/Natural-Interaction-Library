package com.vsu.nil.widgets

import com.primesense.NITE.HandPointContext
import java.awt.*
import java.util.*
import javax.swing.*

/**
 * Created by Thor on 03.05.2016.
 */

class HandPanel(hands: MutableMap<Int, Point>?) : JPanel() {
    private val hands: MutableMap<Int, Point> = hands ?: mutableMapOf()
    private val pDiam = 20

    constructor(): this(null)

    override fun paintComponent(g: Graphics) {
        println("debug: repainting handPanel: ${hands.size} hands")
        super.paintComponent(g)
        //drawing hands' points
        val color = g.color
        g.color = Color.black
        for (hand in hands) {
            val point = hand.value
            g.drawOval(point.x-pDiam/2, point.y-pDiam/2, pDiam, pDiam)

        }
        g.color = color
    }

    fun updateHand(hand: HandPointContext) {
        val position = hand.position
        println("debug: updating ${hand.id} hand: (${position.x.toInt()}, ${position.y.toInt()})")
        hands.put(hand.id, Point(position.x.toInt(), position.y.toInt()))
        parent.repaint()
//        revalidate()
    }
    fun deleteHand(id: Int) {
        hands.remove(id)
        parent.repaint()
//        revalidate()
    }
//    fun updateHands(hands: Set<Point>) {
//        this.hands = hands
//    }
}