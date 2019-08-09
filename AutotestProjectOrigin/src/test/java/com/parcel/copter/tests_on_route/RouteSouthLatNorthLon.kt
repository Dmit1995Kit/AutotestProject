package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase

class RouteSouthLatNorthLon:TestCase() {
    //юг-север
    private val deltaAsimut = 4

    private val southlatnorthlon = SouthLatNorthLon()
    private val southlatnorthlon_1 = SouthLatNorthLon_1()

    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(southlatnorthlon)
        arrayRoute.add(southlatnorthlon_1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for South latitude and North longitude - - - - - ")
            println("!!! При минимальном значении азимута  с 1 до 10 + и выше, возникает ошибка, некорректно выдает результат")
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
                    TestCase.assertTrue(false)
                }
                println("Asimut count correctly.")
                TestCase.assertTrue(true)
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
                    TestCase.assertTrue(false)
                }
                println("Asimut count correctly.")
                TestCase.assertTrue(true)
            }
        }
    }
    fun classSwitch(maker: Any): CreatingClasses_7 {
        if (maker.equals(southlatnorthlon))
            return SwitchingSouthLatNorthLon()
        else if(maker.equals(southlatnorthlon_1))
            return SwitchingSouthLatNorthLon_1()

        throw RuntimeException("Error $maker")
    }
}
class SouthLatNorthLon : CoordinateSystem_7 {
    //маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {

        route.points.add(MapPoint(-15.3738100077657,  28.325566406248043, 0, 0))
        route.points.add(MapPoint(-15.256734920461017,28.329434703131223, 0, 1))
        route.points.add(MapPoint(-15.173512483257293,28.333342389094, 0, 2))
        route.points.add(MapPoint(-15.12054167915911,28.33613160529888, 0, 3))

        asimuts.add(1.837)
        asimuts.add(2.595)
        asimuts.add(2.910)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class SouthLatNorthLon_1 : CoordinateSystem_7 {
    //маршрут по дальнему расстоянию расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {

        route_1.points.add(MapPoint(41.973137350800776, 101.0715380859338, 0, 0))
        route_1.points.add(MapPoint(42.35081181979242, 101.09315185546559, 0, 1))
        route_1.points.add(MapPoint(42.536945263999115, 101.10825805663683, 0, 2))
        route_1.points.add(MapPoint(42.62321120514787, 101.12473754882303, 0, 3))

        asimuts_1.add(2.431)
        asimuts_1.add(3.422)
        asimuts_1.add(8.001)
    }
    override fun createRouts(): Route {
        return route_1
    }

    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_7 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_7 {
    fun createCoordinates(): CoordinateSystem_7
}

class SwitchingSouthLatNorthLon : CreatingClasses_7 {
    override fun createCoordinates(): CoordinateSystem_7 {
        return SouthLatNorthLon()
    }
}
class SwitchingSouthLatNorthLon_1 : CreatingClasses_7 {
    override fun createCoordinates(): CoordinateSystem_7 {
        return SouthLatNorthLon_1()
    }
}