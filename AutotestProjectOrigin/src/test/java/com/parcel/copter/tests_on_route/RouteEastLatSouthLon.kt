package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase
//Восток-Юг
class RouteEastLatSouthLon:TestCase() {
    private val deltaAsimut = 4

    private val eastlatsouthlon = EastLatSouthLon()
    private val eastlatsouthlon_1 = EastLatSouthLon_1()

    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(eastlatsouthlon)
        arrayRoute.add(eastlatsouthlon_1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for East latitude and South longitude - - - - - ")
                println("!!! При превышении азимута с 275 до 300 + и выше, возникает ошибка, некорректно выдает результат")
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
    fun classSwitch(maker: Any): CreatingClasses_5 {
        if (maker.equals(eastlatsouthlon))
            return SwitchingEastLatSouthLon()
        else if(maker.equals(eastlatsouthlon_1))
            return SwitchingEastLatSouthLon_1()

        throw RuntimeException("Error $maker")
    }
}
class EastLatSouthLon : CoordinateSystem_5 {
    //маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {
        route.points.add(MapPoint(59.1121088514933,99.10243661856319, 0, 0))
        route.points.add(MapPoint(59.206825426278606,98.82283424885125, 0, 1))
        route.points.add(MapPoint(59.25186982722563,98.65529274494405, 0, 2))
        route.points.add(MapPoint(59.26804308312062,98.55641579181733, 0, 3))

        asimuts.add(303.53)
        asimuts.add(297.795)
        asimuts.add(287.788)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class EastLatSouthLon_1 : CoordinateSystem_5 {
    //маршрут по дальнему расстоянию расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {
        route_1.points.add(MapPoint(47.49257515474254, 27.718769626372126, 0, 0))
        route_1.points.add(MapPoint(47.74077613405668, 26.653644795720155, 0, 1))
        route_1.points.add(MapPoint(47.937709136591735, 25.73353981524899, 0, 2))
        route_1.points.add(MapPoint(48.0386159321577, 25.201470954380643, 0, 3))

        asimuts_1.add(289.409)
        asimuts_1.add(288.027)
        asimuts_1.add(286.018)
    }
    override fun createRouts(): Route {
        return route_1
    }

    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_5 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_5 {
    fun createCoordinates(): CoordinateSystem_5
}

class SwitchingEastLatSouthLon : CreatingClasses_5 {
    override fun createCoordinates(): CoordinateSystem_5 {
        return EastLatSouthLon()
    }
}
class SwitchingEastLatSouthLon_1 : CreatingClasses_5 {
    override fun createCoordinates(): CoordinateSystem_5 {
        return EastLatSouthLon_1()
    }
}
