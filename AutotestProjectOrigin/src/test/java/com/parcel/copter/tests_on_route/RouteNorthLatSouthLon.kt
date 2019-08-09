package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase

class RouteNorthLatSouthLon:TestCase() {

    private val deltaAsimut = 4

    private val northLatsouthLon = NorthLatSouthLon()
    private val northlatsouthlon_1 = NorthLatSouthLon_1()


    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(northLatsouthLon)
        arrayRoute.add(northlatsouthlon_1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for North latitude and South longitude - - - - - ")
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
    fun classSwitch(maker: Any): CreatingClasses_3 {
        if (maker.equals(northLatsouthLon))
            return SwitchingNorthLatSouthLon()
        else if(maker.equals(northlatsouthlon_1))
            return SwitchingNorthLatSouthLon_1()

        throw RuntimeException("Error $maker")
    }
}
class NorthLatSouthLon : CoordinateSystem_3 {
    //маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {
        route.points.add(MapPoint(68.87451691651262, 169.3718305947156, 0, 0))
        route.points.add(MapPoint(68.12800619426241, 169.38148517009418, 0, 1))
        route.points.add(MapPoint(67.54621614785677, 169.39521808024955, 0, 2))
        route.points.add(MapPoint(67.20780002074665, 169.37873858806134, 0, 3))

        asimuts.add(179.724)
        asimuts.add(179.483)
        asimuts.add(181.081)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class NorthLatSouthLon_1 : CoordinateSystem_3 {
    //маршрут по дальнему расстоянию расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {
        route_1.points.add(MapPoint(78.38722128624136, -44.294062500000344, 0, 0))
        route_1.points.add(MapPoint(77.67221004253472, -44.36308593750032, 0, 1))
        route_1.points.add(MapPoint(77.24751605544041, -44.25322265625035, 0, 2))
        route_1.points.add(MapPoint(77.16660283491849, -44.24498291015842, 0, 3))

        asimuts_1.add(181.181)
        asimuts_1.add(176.732)
        asimuts_1.add(178.704)
    }
    override fun createRouts(): Route {
        return route_1
    }

    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_3 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_3 {
    fun createCoordinates(): CoordinateSystem_3
}

class SwitchingNorthLatSouthLon : CreatingClasses_3 {
    override fun createCoordinates(): CoordinateSystem_3 {
        return NorthLatSouthLon()
    }
}
class SwitchingNorthLatSouthLon_1 : CreatingClasses_3 {
    override fun createCoordinates(): CoordinateSystem_3 {
        return NorthLatSouthLon_1()
    }
}