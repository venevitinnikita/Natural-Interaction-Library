package com.vsu.nil.kinect

import com.primesense.NITE.*
import org.OpenNI.*

/**
 * Created by Thor on 03.05.2016.
 */

class HandObserver<T : EventArgs>(gController: GestureController) : IObserver<T> {
    val gController = gController

    override fun update(observable: IObservable<T>,
                        args: T) {
        //TODO проверять класс T, а не переменную аргументов
        when (args) {
            is IdEventArgs -> {
                //hand was destroyed
                println("hand ${args.id} was destroyed")
                gController.deleteHand(args.id)
            }
            is HandEventArgs -> {
                //hand was recognized
//                println("hand ${args.hand.id} was recognized at (${args.hand.position.x},${args.hand.position.y},${args.hand.position.z})")
                gController.updateHand(args.hand)
            }
            is NullEventArgs -> {
                //no hand found
                println("no hand was detected")
            }
        }
    }
}