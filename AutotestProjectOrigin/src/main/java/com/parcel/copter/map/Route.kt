package com.parcel.copter.map

import com.parcel.copter.MapPoint
import com.peertopark.java.geocalc.*
import kotlin.math.asin

/***
 * Объект маршрута.
 */
class Route {

    /**
     * Точки маршрута.
     */
    var points = ArrayList<MapPoint>()

    /**
     * Добавить точку.
     * (автоматически проставляется номер точки)
     */
    fun addPoint(lat: Coordinate, lng: Coordinate,  height : Int) =
        points.add(MapPoint(lat, lng, height, points.size))

    /**
     * Отсортировать так, что бы ближайшие точки были ближе.
     */
    fun sortByNearest(point: Point): ArrayList<MapPoint>
    {

        val pt = ArrayList<MapPoint>()
        points.forEach { pt.add(it) }
        pt.sortBy { EarthCalc.getDistance(point, it)}
        return pt
    }


    /**
     * Получить азимут движения до ближайшей точки.
     */
    fun getAsimutToNearestPoint(currentPoint: Point): Double {
        val nextPoint = getNeraestNextPoint(currentPoint)
        val latA = DegreeCoordinate(currentPoint.latitude)
        val lonA = DegreeCoordinate(nextPoint.longitude)
        val pointA = Point(latA,lonA)

        val latOCurrent = DegreeCoordinate(currentPoint.latitude)
        val lonOCurrent = DegreeCoordinate(0.0)
        val pointOCurrent = Point(latOCurrent,lonOCurrent)

        val latONextPoint = DegreeCoordinate(nextPoint.latitude)
        val lonONextPoint = DegreeCoordinate(0.0)
        val pointONextPoint = Point(latONextPoint,lonONextPoint)

        val pointANextPoint = EarthCalc.getDistance(pointA , nextPoint)
        val nextPointCurentPoint = EarthCalc.getDistance(nextPoint , currentPoint)
        if(EarthCalc.getDistance(nextPoint , pointONextPoint)>EarthCalc.getDistance(currentPoint , pointOCurrent ))
            return 90.0 + Math.toDegrees(asin(pointANextPoint/nextPointCurentPoint))
        else
            return 360.0 -(90.0 + Math.toDegrees(asin(pointANextPoint/nextPointCurentPoint)))

    }

    /**
     * Получить следующую точку к которой надо лететь.
     */
    fun getNeraestNextPoint(myPoint: Point): MapPoint
    {
        val sortedPoints = sortByNearest(myPoint)
        return if(sortedPoints[0].number<sortedPoints[1].number)
            sortedPoints[1]
        else
            sortedPoints[0]

    }




/*
    fun getDistanseToPoint(point: MapPoint): Long
    {
        var lat: Coordinate = GPSCoordinate(41.0, .1212)
        var lng: Coordinate = GPSCoordinate(11.0, .2323)
        val point = Point(lat, lng)

        lat = DegreeCoordinate(51.4613418)
        lng = DegreeCoordinate(-0.3035466)
        val point2 = Point(lat, lng)
        println("Distance is " + EarthCalc.getDistance(point2, point) / 1000 + " km")
    }
    */

}



