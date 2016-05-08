package com.vsu.nil.kinect

import com.primesense.NITE.SessionManager
import org.OpenNI.Context
import org.OpenNI.GestureGenerator
import org.OpenNI.HandsGenerator

/**
 * Created by Thor on 02.05.2016.
 */

class KinectSensor(gController: GestureController): Runnable {
    val context: Context
    val sessionManager: SessionManager
    private var isRunning = true

    init {
        context = Context()
        println("Kinect context initialized")

        val hands = HandsGenerator.create(context);
        hands.SetSmoothing(0.1f);
        println("Hands generator initialized")

        GestureGenerator.create(context)
        println("Gesture generator initialized")

        context.startGeneratingAll()
        println("Started generating all...")

        sessionManager = SessionManager(context, "Click,Wave", "RaiseHand")
        sessionManager.setSessionEvents()
        sessionManager.addNITEListener { initPointControl(gController) }
        println("Session manager initialized")

        Thread(this).start()
    }

    override fun run() {
        println("Started running refreshing loop...")
        while (isRunning) {
            context.waitAndUpdateAll()
            sessionManager.update(context)
        }
    }
    fun stop() {
        println("Stopping kinect...")
        isRunning = false
    }
}