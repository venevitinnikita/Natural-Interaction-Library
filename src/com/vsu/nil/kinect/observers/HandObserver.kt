package com.vsu.nil.kinect.observers

import org.OpenNI.EventArgs
import org.OpenNI.IObserver

/**
 * Created by Thor on 03.05.2016.
 */

abstract class HandObserver<T : EventArgs> : IObserver<T>