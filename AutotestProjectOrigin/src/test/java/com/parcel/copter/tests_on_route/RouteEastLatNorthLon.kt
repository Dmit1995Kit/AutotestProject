package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase
//восток-север
class RouteEastLatNorthLon:TestCase() {
    private val deltaAsimut = 4

    private val eastlatnorthlon = EastLatNorthLon()
    private val eastlatnorthlon_1 = EastLatNorthLon_1()

    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(eastlatnorthlon)
        arrayRoute.add(eastlatnorthlon_1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for East latitude and North longitude - - - - - ")
            if (i_1 > 0)
                println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -")
            for (i in 0 until classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts().points.size - 1) {
                val a = classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts()
                    .getAsimutToNearestPoint(classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts().points[i])
                if (a > classSwitch(arrayRoute.get(i_1)).createCoordinates().createAsimuts()[i] + deltaAsimut || a < classSwitch(
                        arrayRoute.get(i_1)
                    ).createCoordinates().createAsimuts()[i] - deltaAsimut
                ) {
                    println(
                        "Asimut count not correctly.(pointNumber = ${classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts().points[i + 1].number} " +
                                "wait =${classSwitch(arrayRoute.get(i_1)).createCoordinates().createAsimuts()[i]}, have =$a )"
                    )
                    assertTrue(false)
                }
                println("Asimut count correctly.")
                assertTrue(true)
                println(a)
            }
            for (i in classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts().points.size - 1 until 0) {
                val a = classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts()
                    .getAsimutToNearestPoint(classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts().points[i])
                if (a > classSwitch(arrayRoute.get(i_1)).createCoordinates().createAsimuts()[i] + deltaAsimut || a < classSwitch(
                        arrayRoute.get(i_1)
                    ).createCoordinates().createAsimuts()[i] - deltaAsimut
                ) {
                    println(
                        "Asimut count not correctly.(pointNumber = ${classSwitch(arrayRoute.get(i_1)).createCoordinates().createRouts().points[i - 1].number} " +
                                "wait =${classSwitch(arrayRoute.get(i_1)).createCoordinates().createAsimuts()[i]}, have =$a )"
                    )
                    assertTrue(false)
                }
                println("Asimut count correctly.")
                assertTrue(true)
            }
        }
    }
    fun classSwitch(maker: Any): CreatingClasses_4 {
        if (maker.equals(eastlatnorthlon))
            return SwitchingEastLatNorthLon()
        else if(maker.equals(eastlatnorthlon_1))
            return SwitchingEastLatNorthLon_1()

        throw RuntimeException("Error $maker")
    }
}
class EastLatNorthLon : CoordinateSystem_4 {
    //маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {
        route.points.add(MapPoint(56.697106168618,   60.51001464843404, 0, 0))
        route.points.add(MapPoint(56.64895479017189, 60.36898830985241, 0, 1))
        route.points.add(MapPoint(56.623224018208916,60.29414394950013, 0, 2))
        route.points.add(MapPoint(56.609973367689676, 60.24642208670321, 0, 3))

        asimuts.add(238.252)
        asimuts.add(238.021)
        asimuts.add(243.243)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class EastLatNorthLon_1 : CoordinateSystem_4 {
    //маршрут по дальнему расстоянию расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {
        route_1.points.add(MapPoint(49.84208055555568, 24.025792236318352, 0, 0))
        route_1.points.add(MapPoint(49.45179086929297, 22.88878658865175, 0, 1))
        route_1.points.add(MapPoint(49.13652612630167, 21.96181515310313, 0, 2))
        route_1.points.add(MapPoint(48.92884185026294, 21.332847867945237, 0, 3))

        asimuts_1.add(242.571)
        asimuts_1.add(242.810)
        asimuts_1.add(243.506)
    }
    override fun createRouts(): Route {
        return route_1
    }

    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_4 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_4 {
    fun createCoordinates(): CoordinateSystem_4
}

class SwitchingEastLatNorthLon : CreatingClasses_4 {
    override fun createCoordinates(): CoordinateSystem_4 {
        return EastLatNorthLon()
    }
}
class SwitchingEastLatNorthLon_1 : CreatingClasses_4 {
    override fun createCoordinates(): CoordinateSystem_4 {
        return EastLatNorthLon_1()
    }
}