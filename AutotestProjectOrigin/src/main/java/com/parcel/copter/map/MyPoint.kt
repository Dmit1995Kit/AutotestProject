package com.parcel.copter.map

import com.peertopark.java.geocalc.Coordinate
import com.peertopark.java.geocalc.Point

class MyPoint(lat: Coordinate, lng: Coordinate) : Point(lat,lng) {

    var height = 0
    var speed = 0
    var asimut = 0

    //private var latitude: Double = 0.0

}