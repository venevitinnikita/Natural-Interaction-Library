package com.vsu.nil.widgets

import com.primesense.NITE.HandPointContext
import com.vsu.nil.kinect.KinectWindowListener
import java.awt.*
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JLayeredPane

/**
 * Created by Thor on 13.06.2016.
 */

class NIFrame : JFrame() {
    private val container = object: JComponent(){}
    private val statePanel = StatePanel()

    init {
        val contentPane = this.contentPane
        val layeredPane = JLayeredPane()

        statePanel.bounds = Rectangle(0, 0, 800, 600)
        statePanel.background = Color.RED

        layeredPane.add(container)
        layeredPane.add(statePanel)
        layeredPane.setLayer(container, 0)
        layeredPane.setLayer(statePanel, Int.MAX_VALUE)

        contentPane.add(layeredPane, BorderLayout.CENTER)

        addWindowListener(KinectWindowListener())

        size = Dimension(800, 600)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    //TODO сделать по-человечески
    override fun add(comp: Component): Component {
        container.add(comp)
        return comp
    }

    fun updateHand(hand: HandPointContext) = statePanel.updateHand(hand)
    fun deleteHand(id: Int) = statePanel.deleteHand(id)
    fun calibration(time: Int) = statePanel.calibration(time)
}