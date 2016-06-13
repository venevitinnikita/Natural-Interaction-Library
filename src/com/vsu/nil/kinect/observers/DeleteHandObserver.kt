package com.vsu.nil.kinect.observers

import com.primesense.NITE.IdEventArgs
import com.vsu.nil.kinect.gController
import org.OpenNI.IObservable

/**
 * Created by Thor on 05.06.2016.
 */

object DeleteHandObserver : HandObserver<IdEventArgs>() {

    override fun update(observable: IObservable<IdEventArgs>, args: IdEventArgs) {
        //hand was destroyed
        println("hand ${args.id} was destroyed")
        gController.deleteHand(args.id)
    }
}