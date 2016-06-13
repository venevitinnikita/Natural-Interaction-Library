package com.vsu.nil.kinect

import com.primesense.NITE.SessionManager
import org.openni.Context
import org.openni.GestureGenerator
import org.openni.HandsGenerator
import org.openni.StatusException

/**
 * Created by Thor on 02.05.2016.
 */

class KinectSensor(gController: GestureController): Runnable {
    val context: Context?
    val sessionManager: SessionManager?

    init {
        try {
            context = Context()
            context.globalMirror = true
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

            gController.status = TrackingStatus.NO_USER
            Thread(this).start()
        } catch (ex: StatusException) {
            println(ex)
            ex.printStackTrace()
            println("Kinect wasn't initialized properly")
            println("Working in simple mode without Kinect...")
            gController.status = TrackingStatus.OFF
            context = null
            sessionManager = null
        }
    }

    override fun run() {
        println("Started running refreshing loop...")
        while (gController.status != TrackingStatus.OFF) {
            context?.waitAndUpdateAll()
            sessionManager?.update(context)
        }
    }
    fun stop() {
        println("Stopping kinect...")
        gController.status = TrackingStatus.OFF
    }
}