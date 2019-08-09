package com.parcel.copter.tests_on_route

import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase

class RouteEquatorPrimeMeridian: TestCase() {
//пересечение экватора и нулевого меридиана

    private val deltaAsimut = 4

    private val equatorMeridian = EquatorMeridianLatLon()
    private val equatorMeridian_1 = EquatorMeridianLatLon_1()

    private val arrayRoute = ArrayList<Any>()

    fun testValueAsimutsNorthEast() {
        arrayRoute.add(equatorMeridian)
        arrayRoute.add(equatorMeridian_1)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates for latitude and longitude the Equator Meridian - - - - - ")
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
    fun classSwitch(maker: Any): CreatingClasses_8 {
        if (maker.equals(equatorMeridian))
            return SwitchingLatLonEquatorMeridian()
        else if(maker.equals(equatorMeridian_1))
            return SwitchingLatLonEquatorMeridian_1()

        throw RuntimeException("Error $maker")
    }
}
class EquatorMeridianLatLon : CoordinateSystem_8 {
    //маршрут по близкому расстоянию (тип маршрута линейный)
    private val route = Route()
    private val asimuts = arrayListOf<Double>()
    init {

        route.points.add(MapPoint(13.47541376568628,  13.949612731925441, 0, 0))
        route.points.add(MapPoint(13.411810938676343,14.026559918938249, 0, 1))
        route.points.add(MapPoint(13.387443203540707,14.05917558055831, 0, 2))
        route.points.add(MapPoint(13.377359267305907,14.072221845205862, 0, 3))

        asimuts.add(130.172)
        asimuts.add(127.521)
        asimuts.add(128.466)
    }
    override fun createRouts(): Route {
        return route
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts
    }
}
class EquatorMeridianLatLon_1 : CoordinateSystem_8 {
    //маршрут по дальнему расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {

        route_1.points.add(MapPoint(14.113438906754741,  14.073037261951642, 0, 0))
        route_1.points.add(MapPoint(13.871634081123673,13.485826581531075, 0, 1))
        route_1.points.add(MapPoint(13.756218172820768,13.061479657701863, 0, 2))
        route_1.points.add(MapPoint(13.689089354523988,12.848619550279087, 0, 3))

        asimuts_1.add(247.206)
        asimuts_1.add(254.404)
        asimuts_1.add(252.040)
    }
    override fun createRouts(): Route {
        return route_1
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}
interface CoordinateSystem_8 {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses_8 {
    fun createCoordinates(): CoordinateSystem_8
}

class SwitchingLatLonEquatorMeridian : CreatingClasses_8 {
    override fun createCoordinates(): CoordinateSystem_8 {
        return EquatorMeridianLatLon()
    }
}
class SwitchingLatLonEquatorMeridian_1 : CreatingClasses_8 {
    override fun createCoordinates(): CoordinateSystem_8 {
        return EquatorMeridianLatLon_1()
    }
}