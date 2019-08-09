package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase

class RouteSouthPole:TestCase() {
    private val deltaAsimut = 4

    private val southPole = SouthPoleLatLon()

    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(southPole)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for latitude and longitude the South Pole - - - - - ")
//            println("!!! При минимальном значении азимута  с 1 до 10 + и выше, возникает ошибка, некорректно выдает результат")
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
    fun classSwitch(maker: Any): CreatingClasses_10 {
        if (maker.equals(southPole))
            return SwitchingLatLonSouthPole()

        throw RuntimeException("Error $maker")
    }
}
class SouthPoleLatLon : CoordinateSystem_10 {
    //маршрут по дальнему расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {

        route.points.add(MapPoint(-85.05115864318049,  -73.81003464868078, 0, 0))
        route.points.add(MapPoint(-85.05087932372953,-70.12186868957775, 0, 1))
        route.points.add(MapPoint(-85.04898326160175,-68.03446634582815, 0, 2))
        route.points.add(MapPoint(-85.05372205847442,-66.69413431457819, 0, 3))

        asimuts.add(91.787)
        asimuts.add(90.437)
        asimuts.add(93.015)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}

interface CoordinateSystem_10 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_10 {
    fun createCoordinates(): CoordinateSystem_10
}

class SwitchingLatLonSouthPole : CreatingClasses_10 {
    override fun createCoordinates(): CoordinateSystem_10 {
        return SouthPoleLatLon()
    }
}
