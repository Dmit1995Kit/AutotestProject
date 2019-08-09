package com.parcel.copter.tests_on_route
import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import junit.framework.TestCase
import org.junit.Assert
//север на восток
class RouteNorthLatEastLon:TestCase() {

    private val deltaAsimut = 4

    private val northlateastlon_1 = NorthLatEastLon_1()
    private val northlateastlon_2 = NorthLatEastLon_2()
    private val northlateastlon_3 = NorthLatEastLon_3()

    private val arrayRoute = ArrayList<Any>()


    fun testValueAsimutsNorthEast() {
         arrayRoute.add(northlateastlon_1)
         arrayRoute.add(northlateastlon_2)
         arrayRoute.add(northlateastlon_3)

        for (i_1 in 0 until arrayRoute.size) {
            if (i_1 == 0)
                println("- - - - -  Сoordinates of the Northern latitude and Eastern longitude - - - - - ")
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
                Assert.assertTrue(true)
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
    fun classSwitch(maker: Any): CreatingClasses {
         if(maker.equals(northlateastlon_1))
            return SwitchingNorthLatEastLon_1()
        else if (maker.equals(northlateastlon_2))
            return SwitchingNorthLatEastLon_2()
        else if(maker.equals(northlateastlon_3))
            return SwitchingNorthLatEastLon_3()

        throw RuntimeException("Error $maker")
    }

}

class NorthLatEastLon_1 : CoordinateSystem {
//маршрут по близкому расстоянию (тип маршрута линейный)
    private val route_1 = Route()
    private val asimuts_1 = arrayListOf<Double>()
    init {
        route_1.points.add(MapPoint(56.92196161328465, 23.97954007683103, 0, 0))
        route_1.points.add(MapPoint(56.92183237403636, 23.982292699793238, 0, 1))
        route_1.points.add(MapPoint(56.921785433999, 23.983869838692236, 0, 2))
        route_1.points.add(MapPoint(56.92172089226556, 23.985092926001947, 0, 3))

        asimuts_1.add(94.906)
        asimuts_1.add(93.121)
        asimuts_1.add(95.522)

    }
    override fun createRouts(): Route {
        return route_1
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_1
    }
}

class NorthLatEastLon_2 : CoordinateSystem {
    //маршрут по дальнему расстоянию (тип маршрута линейный)
    private val route_2 = Route()
    private val asimuts_2 = arrayListOf<Double>()
    init {
        route_2.points.add(MapPoint(36.90073413342794, 30.69809829710445, 0, 0))
        route_2.points.add(MapPoint(36.86531867791862, 31.744530487019468, 0, 1))
        route_2.points.add(MapPoint(36.8520762659444, 32.62343673701755, 0, 2))
        route_2.points.add(MapPoint(36.834416116186055,33.29909591670303, 0, 3))

        asimuts_2.add(92.098)
        asimuts_2.add(90.815)
        asimuts_2.add(91.668)
    }
    override fun createRouts(): Route {
        return route_2
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_2
    }
}

class NorthLatEastLon_3 : CoordinateSystem {
    //маршрут по дальнему расстоянию (тип маршрута изогнутый)
    private val route_3 = Route()
    private val asimuts_3 = arrayListOf<Double>()
    init {
        route_3.points.add(MapPoint(37.484114929608374,32.41883193968669, 0, 0))
        route_3.points.add(MapPoint(37.58570796144828, 32.661888885466134, 0, 1))
        route_3.points.add(MapPoint(37.52776372299497, 32.88985519405831, 0, 2))
        route_3.points.add(MapPoint(37.622855818005156,33.12056808468225, 0, 3))

        asimuts_3.add(62.232)
        asimuts_3.add(107.708)
        asimuts_3.add(62.453)
    }
    override fun createRouts(): Route {
        return route_3
    }
    override fun createAsimuts(): ArrayList<Double> {
        return asimuts_3
    }
}

interface CoordinateSystem {
    fun createRouts(): Route
    fun createAsimuts(): ArrayList<Double>
}
interface CreatingClasses {
    fun createCoordinates(): CoordinateSystem
}

class SwitchingNorthLatEastLon_1 : CreatingClasses {
    override fun createCoordinates(): CoordinateSystem {
        return NorthLatEastLon_1()
    }
}
class SwitchingNorthLatEastLon_2 : CreatingClasses {
    override fun createCoordinates(): CoordinateSystem {
        return NorthLatEastLon_2()
    }
}
class SwitchingNorthLatEastLon_3 : CreatingClasses {
    override fun createCoordinates(): CoordinateSystem {
        return NorthLatEastLon_3()
    }
}