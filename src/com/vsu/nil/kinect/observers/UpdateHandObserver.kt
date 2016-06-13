package com.vsu.nil.kinect.observers

import com.primesense.NITE.HandEventArgs
import com.primesense.NITE.HandPointContext
import com.vsu.nil.kinect.GestureController
import com.vsu.nil.kinect.TrackingStatus
import com.vsu.nil.kinect.gController
import org.OpenNI.IObservable

/**
 * Created by Thor on 05.06.2016.
 */

object UpdateHandObserver : HandObserver<HandEventArgs>() {

    override fun update(observable: IObservable<HandEventArgs>, args: HandEventArgs) {
        //hand movement was recognized
        println("hand ${args.hand.id} was recognized at (${args.hand.position.x},${args.hand.position.y},${args.hand.position.z})")
        if (gController.status == TrackingStatus.TRACKING) {
            val id = args.hand.id
            val position = Mapper.getWindowPoint(args.hand.position)
            val time = args.hand.time
            val confidence = args.hand.confidence

            val hand = HandPointContext(id, position, time, confidence)
            gController.updateHand(hand)
        }
    }
}