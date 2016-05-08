package com.vsu.nil.kinect

import com.primesense.NITE.*
import org.OpenNI.IObservable
import org.OpenNI.IObserver

/**
 * Created by Thor on 02.05.2016.
 */

//for NITE
//observers
private val sessionStartEventObserver = SessionObserver<PointEventArgs>()
private val sessionEndEventObserver = SessionObserver<NullEventArgs>()
private var handEventHObserver: HandObserver<HandEventArgs>? = null
private var idEventHObserver: HandObserver<IdEventArgs>? = null
private var nullEventHObserver: HandObserver<NullEventArgs>? = null
//val handEventHObserver = HandObserver<HandEventArgs>()
//val idEventHObserver = HandObserver<IdEventArgs>()
//val nullEventHObserver = HandObserver<NullEventArgs>()

fun handEventHObserver (gController: GestureController): HandObserver<HandEventArgs> {
    if (handEventHObserver == null) {
        handEventHObserver=HandObserver<HandEventArgs>(gController)}
    val observer = handEventHObserver ?: HandObserver<HandEventArgs>(gController)
    return observer
}
fun idEventHObserver (gController: GestureController): HandObserver<IdEventArgs> {
    if (idEventHObserver == null) {
        idEventHObserver=HandObserver<IdEventArgs>(gController)}
    val observer = idEventHObserver ?: HandObserver<IdEventArgs>(gController)
    return observer
}
fun nullEventHObserver (gController: GestureController): HandObserver<NullEventArgs> {
    if (nullEventHObserver == null) {
        nullEventHObserver=HandObserver<NullEventArgs>(gController)}
    val observer = nullEventHObserver ?: HandObserver<NullEventArgs>(gController)
    return observer
}

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

    pointControl.pointCreateEvent.addObserver(handEventHObserver(gController))
    pointControl.pointUpdateEvent.addObserver(handEventHObserver(gController))
    pointControl.pointDestroyEvent.addObserver(idEventHObserver(gController))
    pointControl.noPointsEvent.addObserver(nullEventHObserver(gController))

    return pointControl
}