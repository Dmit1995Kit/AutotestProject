package com.parcel.copter

import com.peertopark.java.geocalc.*

/**
 * Точка на карте.
 */
class MapPoint(lat: Coordinate, lng: Coordinate, var height : Int,  var number: Int) : Point(lat,lng) {

    constructor(lat: Double, lng: Double, height: Int, number: Int):
            this(DegreeCoordinate(lat), DegreeCoordinate(lng), height, number)

    fun equals(mapPoint: MapPoint):Boolean =
        this.latitude == mapPoint.latitude &&
                    this.longitude == mapPoint.longitude &&
                    this.height == mapPoint.height &&
                    this.number == mapPoint.number
}