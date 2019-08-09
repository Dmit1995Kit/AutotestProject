package com.parcel.copter

import com.parcel.copter.map.Route
import junit.framework.TestCase
//http://www.garmin.com.ua/tools/calc.php?typ=0&n1=55.688381&e1=37.770240&n2=55.687803&e2=37.770523
class RouteTest  : TestCase(){

    private val deltaAsimut = 4
    private val route = Route()

    val asimuts = arrayListOf<Double>()

    init {
        route.points.add(MapPoint(55.688381, 37.770240, 0, 0))
        route.points.add(MapPoint(55.687803, 37.770523, 0, 1))
        route.points.add(MapPoint(55.687410, 37.770591, 0, 2))
        route.points.add(MapPoint(55.687289, 37.770678, 0, 3))

        asimuts.add(164.570)
        asimuts.add(174.430)
        asimuts.add(157.940)

    }
    fun testGetAsimut()
    {
        for(i in 0 until route.points.size-1)
        {
            val a = route.getAsimutToNearestPoint(route.points[i])
            if(a>asimuts[i]+deltaAsimut ||  a<asimuts[i]-deltaAsimut)
            {
                println("Asimut count not correctly.(pointNumber = ${route.points[i+1].number} " +
                        "wait =${asimuts[i]}, have =$a )")
                assertTrue(false)
            }
            println("Asimut count correctly.")
            assertTrue(true)
            println(a)
        }

        for(i in route.points.size-1 until 0)
        {
            val a = route.getAsimutToNearestPoint(route.points[i])
            if(a>asimuts[i]+deltaAsimut ||  a<asimuts[i]-deltaAsimut)
            {
                println("Asimut count not correctly.(pointNumber = ${route.points[i-1].number} " +
                        "wait =${asimuts[i]}, have =$a )")
                assertTrue(false)
            }
            println("Asimut count correctly.")

            assertTrue(true)
        }
    }
}