package com.vsu.nil.kinect.observers

import com.primesense.NITE.HandEventArgs
import com.vsu.nil.kinect.TrackingStatus
import com.vsu.nil.kinect.gController
import org.openni.IObservable
import org.openni.Point3D
import java.lang.Math.*

/**
 * Created by Thor on 05.06.2016.
 */

object CalibrateHandObserver : HandObserver<HandEventArgs>() {
    private val calibrationTime = 5
    private val handsPoints = mutableMapOf<Int, MutableList<Point3D>>()

    override fun update(observable: IObservable<HandEventArgs>, args: HandEventArgs) {
        //hand was recognized
        if (gController.status == TrackingStatus.CALIBRATION) {
            println("calibrating...")
            val position = args.hand.position
            if (position != null) {
                if (!handsPoints.contains(args.hand.id)) {
                    handsPoints.put(args.hand.id, mutableListOf())
                }
                handsPoints.get(args.hand.id)?.add(position)
            }
        }
    }

    fun calibrate(handId: Int) {
        Runnable {
            if (!handsPoints.isEmpty()) {
                handsPoints.clear()
            }
            gController.startCalibration(calibrationTime)
            println("calibration started")
//            Thread.sleep(calibrationTime.toLong()*1000)

            val minY = getMinByY(handId)
            val maxY = getMaxByY(handId)
            if (minY != null
                    && maxY != null) {
                var x = (minY.x + maxY.x)/2
                var y = (minY.y + maxY.y)/2
                var z = (minY.z + maxY.z)/2
                val shoulder = Point3D(x, y, z)
                val handLength = (maxY.y - minY.y)/2
                Mapper.workspaceWidth = (handLength * cos(PI/6.0)).toFloat()
                Mapper.workspaceHeight = (handLength * sin(PI/6.0)).toFloat()
                x = shoulder.x - Mapper.workspaceWidth
                y = shoulder.y - Mapper.workspaceHeight
                Mapper.basePoint = Point3D(x, y, z)
                Mapper.workspaceWidth *= 2
                Mapper.workspaceHeight *= 2
                Mapper.updateCoeffs()
                println("calibration ended")
            }
        }.run()
//        gController.status = TrackingStatus.TRACKING
    }

    private fun getMaxByY(handId: Int): Point3D? {
        val points = handsPoints.get(handId);
        var edgePoint: Point3D
        if (points != null) {
            edgePoint = points.get(0)
            for (point in points) {
                if (edgePoint.y < point.y) {
                    edgePoint = point
                }
            }
            return edgePoint
        }
        return null
    }

    private fun getMinByY(handId: Int): Point3D? {
        val points = handsPoints.get(handId);
        var edgePoint: Point3D
        if (points != null) {
            edgePoint = points.get(0)
            for (point in points) {
                if (edgePoint.y > point.y) {
                    edgePoint = point
                }
            }
            return edgePoint
        }
        return null
    }
}