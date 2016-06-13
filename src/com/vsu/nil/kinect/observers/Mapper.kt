package com.vsu.nil.kinect.observers

import org.openni.Point3D

/**
 * Created by Thor on 05.06.2016.
 */

object Mapper {

    var basePoint = Point3D(0f, 0f, 0f)
    var windowWidth = 800f
    var windowHeight = 600f
    var workspaceWidth = windowWidth
    var workspaceHeight = windowHeight

    private var widthCoeff = windowWidth / workspaceWidth
    private var heightCoeff = windowHeight / workspaceHeight

    fun getWindowPoint(point: Point3D): Point3D {
        //workspace coords

        var x = point.x - basePoint.x
        var y = workspaceHeight - (point.y - basePoint.y)
        x *= widthCoeff
        y *= heightCoeff
        println("Mapping..: (${point.x},${point.y},${point.z}) ->> (${x},${y},${point.z})")

        return Point3D(x, y, point.z)
    }

    fun updateCoeffs() {
        widthCoeff = windowWidth / workspaceWidth
        heightCoeff = windowHeight / workspaceHeight
    }
}