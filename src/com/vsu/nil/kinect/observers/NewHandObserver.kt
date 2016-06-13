package com.vsu.nil.kinect.observers

import com.primesense.NITE.HandEventArgs
import org.OpenNI.IObservable

/**
 * Created by Thor on 05.06.2016.
 */

object NewHandObserver : HandObserver<HandEventArgs>() {

    override fun update(observable: IObservable<HandEventArgs>, args: HandEventArgs) {
        //hand was recognized
        println("New hand recognized!")
        val handId = args.hand.id
        CalibrateHandObserver.calibrate(handId)
    }
}