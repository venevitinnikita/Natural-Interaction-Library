package com.vsu.nil.wrappers

import com.primesense.NITE.HandEventArgs
import com.vsu.nil.kinect.observers.HandObserver
import com.vsu.nil.widgets.NIFrame
import org.OpenNI.EventArgs
import org.OpenNI.IObserver
import java.awt.event.WindowListener

/**
 * Created by Thor on 13.06.2016.
 */

interface Device {
    fun getNIFrame(): NIFrame
    fun getNIWindowListener(): WindowListener
    fun addUpdateHandObserver(observer: HandObserver<HandEventArgs>)
    fun <T : EventArgs>addClickObserver(observer: IObserver<T>)
}