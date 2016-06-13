package com.vsu.nil.kinect

import com.primesense.NITE.*
import com.vsu.nil.kinect.observers.*
import org.OpenNI.IObservable
import org.OpenNI.IObserver

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