package com.vsu.nil.kinect.observers

import org.openni.EventArgs
import org.openni.IObserver

/**
 * Created by Thor on 03.05.2016.
 */

abstract class HandObserver<T : EventArgs> : IObserver<T>