package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase

//Юг-запад
class RouteSounthLatWestLon:TestCase() {
    private val deltaAsimut = 4

    private val soutchLatWestLon = SouthLatWestLon()
    private val soutchLatWestLon_1 = SoutchlatWestLon_1()


    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(soutchLatWestLon)
        arrayRoute.add(soutchLatWestLon_1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for southern latitude and Western longitude - - - - - ")
                println("!!!При выставлении отрицательных значений возникает ошибка, некорректные данные")
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
    fun classSwitch(maker: Any): CreatingClasses_1 {
        if (maker.equals(soutchLatWestLon))
            return SwitchingSouthLatWestLon()
        else if(maker.equals(soutchLatWestLon_1))
            return SwitchingSouthLatWestLon_1()

        throw RuntimeException("Error $maker")
    }
}
class SouthLatWestLon : CoordinateSystem_1 {
//маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {
        route.points.add(MapPoint(-15.257854539369447, -59.37554443359936, 0, 0))
        route.points.add(MapPoint(-15.218325655408803, -59.34157104492867, 0, 1))
        route.points.add(MapPoint(-15.198156251729184,   -59.32303161621794, 0, 2))
        route.points.add(MapPoint(-15.19115484073512, -59.315306854255276, 0, 3))

        asimuts.add(39.848)
        asimuts.add(41.575)
        asimuts.add(46.797)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class SoutchlatWestLon_1 : CoordinateSystem_1 {
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {
        route_1.points.add(MapPoint(-18.0910150689129,-63.97812927247203, 0, 0))
        route_1.points.add(MapPoint(-18.117415461880068	,-63.32394332886416, 0, 1))
        route_1.points.add(MapPoint(-18.118728601124776	, -62.85290451050502, 0, 2))
        route_1.points.add(MapPoint(-18.12398107202971,-62.51782150269284, 0, 3))

        asimuts_1.add(92.518)
        asimuts_1.add(90.241)
        asimuts_1.add(90.997)
    }
    override fun createRouts(): Route {
        return route_1
    }

    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_1 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_1 {
    fun createCoordinates(): CoordinateSystem_1
}
class SwitchingSouthLatWestLon : CreatingClasses_1 {
    override fun createCoordinates(): CoordinateSystem_1 {
        return SouthLatWestLon()
    }
}
class SwitchingSouthLatWestLon_1 : CreatingClasses_1 {
    override fun createCoordinates(): CoordinateSystem_1 {
        return SoutchlatWestLon_1()
    }
}
