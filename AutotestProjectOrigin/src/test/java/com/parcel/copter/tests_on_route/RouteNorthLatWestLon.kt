package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase
//северо-запад
class RouteNorthLatWestLon: TestCase() {

    private val deltaAsimut = 4

    private val northLatWestLon = NorthLatWestLon()
    private val northlatwestlon1 = NorthLatWestLon_1()


    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(northLatWestLon)
        arrayRoute.add(northlatwestlon1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for North latitude and Western longitude - - - - - ")
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
    fun classSwitch(maker: Any): CreatingClasses_2 {
        if (maker.equals(northLatWestLon))
            return SwitchingNorthLatWestLon()
        else if(maker.equals(northlatwestlon1))
            return SwitchingNorthLatWestLon_1()

        throw RuntimeException("Error $maker")
    }
}
class NorthLatWestLon : CoordinateSystem_2 {
    //маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {
        route.points.add(MapPoint(13.476261501439648, 2.0663503021909566, 0, 0))
        route.points.add(MapPoint(12.937745420097436, 2.5847557067631066, 0, 1))
        route.points.add(MapPoint(12.473947455892208, 3.0915000915263016, 0, 2))
        route.points.add(MapPoint(12.128246993946343, 3.465035247775056, 0, 3))

        asimuts.add(136.615)
        asimuts.add(133.119)
        asimuts.add(133.408)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class NorthLatWestLon_1 : CoordinateSystem_2 {
    //маршрут по дальнему расстоянию расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {
        route_1.points.add(MapPoint(15.055311749009173, -0.9921368408339923, 0, 0))
        route_1.points.add(MapPoint(15.016470355345792, -0.9696647644243697, 0, 1))
        route_1.points.add(MapPoint(14.997115812947674, -0.953013610860688, 0, 2))
        route_1.points.add(MapPoint(14.986937278354837, -0.9446022033907429, 0, 3))

        asimuts_1.add(150.65)
        asimuts_1.add(140.272)
        asimuts_1.add(141.400)
    }
    override fun createRouts(): Route {
        return route_1
    }

    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_2 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_2 {
    fun createCoordinates(): CoordinateSystem_2
}

class SwitchingNorthLatWestLon : CreatingClasses_2 {
    override fun createCoordinates(): CoordinateSystem_2 {
        return NorthLatWestLon()
    }
}
class SwitchingNorthLatWestLon_1 : CreatingClasses_2 {
    override fun createCoordinates(): CoordinateSystem_2 {
        return NorthLatWestLon_1()
    }
}