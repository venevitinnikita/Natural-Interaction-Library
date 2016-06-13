package com.vsu.nil.kinect.observers

import com.primesense.NITE.NullEventArgs
import com.vsu.nil.kinect.GestureController
import org.OpenNI.IObservable

/**
 * Created by Thor on 05.06.2016.
 */

object NoneHandObserver : HandObserver<NullEventArgs>() {

    override fun update(observable: IObservable<NullEventArgs>, args: NullEventArgs) {
        //no hand found
        println("no hand was detected")
    }
}