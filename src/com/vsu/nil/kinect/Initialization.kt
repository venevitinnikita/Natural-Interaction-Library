package com.vsu.nil.kinect

import com.primesense.NITE.NullEventArgs
import com.primesense.NITE.PointControl
import com.primesense.NITE.PointEventArgs
import com.primesense.NITE.SessionManager
import com.vsu.nil.kinect.observers.*

/**
 * Created by Thor on 02.05.2016.
 */

//for NITE
//observers
private val sessionStartEventObserver = SessionObserver<PointEventArgs>()
private val sessionEndEventObserver = SessionObserver<NullEventArgs>()

fun <T : PointControl> SessionManager.addNITEListener(body: () -> T) {
    val niteListener = body()
    this.addListener(niteListener)
}
fun SessionManager.setSessionEvents() {
    this.sessionStartEvent.addObserver(sessionStartEventObserver)
    this.sessionEndEvent.addObserver(sessionEndEventObserver)
}

fun initPointControl(gController: GestureController): PointControl {
    val pointControl = PointControl()

    pointControl.pointCreateEvent.addObserver(NewHandObserver)
    pointControl.pointUpdateEvent.addObserver(CalibrateHandObserver)
    pointControl.pointUpdateEvent.addObserver(UpdateHandObserver)
    pointControl.pointDestroyEvent.addObserver(DeleteHandObserver)
    pointControl.noPointsEvent.addObserver(NoneHandObserver)

    return pointControl
}