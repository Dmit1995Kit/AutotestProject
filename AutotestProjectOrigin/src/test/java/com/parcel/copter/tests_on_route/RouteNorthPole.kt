package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase

class RouteNorthPole:TestCase() {

    private val deltaAsimut = 4

    private val northPoleLatLon = NorthPoleLatLon()

    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(northPoleLatLon)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for latitude and longitude the North Pole - - - - - ")
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
    fun classSwitch(maker: Any): CreatingClasses_9 {
        if (maker.equals(northPoleLatLon))
            return SwitchingLatLonNorthPole()

        throw RuntimeException("Error $maker")
    }
}
class NorthPoleLatLon : CoordinateSystem_9 {
    //маршрут по дальнему расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {

        route.points.add(MapPoint(82.39880575588755,  -130.69724167991913, 0, 0))
        route.points.add(MapPoint(82.25726291831991	,-128.70212419751934, 0, 1))
        route.points.add(MapPoint(82.14994747405933,-127.42771013501849, 0, 2))
        route.points.add(MapPoint(82.07758377470157,-126.63669451001812, 0, 3))

        asimuts.add(116.993)
        asimuts.add(121.198)
        asimuts.add(123.301)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}

interface CoordinateSystem_9 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_9 {
    fun createCoordinates(): CoordinateSystem_9
}

class SwitchingLatLonNorthPole : CreatingClasses_9 {
    override fun createCoordinates(): CoordinateSystem_9 {
        return NorthPoleLatLon()
    }
}
