package com.vsu.nil.kinect.observers

import com.primesense.NITE.NullEventArgs
import com.primesense.NITE.PointEventArgs
import org.openni.EventArgs
import org.openni.IObservable
import org.openni.IObserver

/**
 * Created by Thor on 07.05.2016.
 */

class SessionObserver<T: EventArgs>: IObserver<T> {
    override fun update(observable: IObservable<T>, args: T) {
        when (args) {
            is PointEventArgs -> {
                println("Session started as hand was recognized")
            }
            is NullEventArgs -> {
                println("Session ended.")
            }
        }
    }

}