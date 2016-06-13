package com.vsu.nil.kinect.observers

import com.primesense.NITE.*
import com.vsu.nil.kinect.GestureController
import org.OpenNI.*

/**
 * Created by Thor on 03.05.2016.
 */

abstract class HandObserver<T : EventArgs> : IObserver<T>