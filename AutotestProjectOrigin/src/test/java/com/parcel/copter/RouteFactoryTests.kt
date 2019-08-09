package com.parcel.copter

import com.parcel.copter.map.Route
import com.parcel.copter.map.RouteFactory
import junit.framework.TestCase

class RouteFactoryTests  : TestCase() {

    private val route = Route()
    init {
        route.points.add(MapPoint(55.688381, 37.770240, 0, 0))
        route.points.add(MapPoint(55.687803, 37.770523, 0, 1))
        route.points.add(MapPoint(55.687410, 37.770591, 0, 2))
        route.points.add(MapPoint(55.687289, 37.770678, 0, 3))
    }

    private val testFileName = "Routes/testRoute.json"
    /**
     * Тестируем создание и удаления файла настроек.
     *
     */
    fun testCreateAndReadFile()
    {
        RouteFactory.saveRouteToFile(testFileName, route)
        val newRoute = RouteFactory.getRoutFromFile(testFileName)
        if(newRoute.points.size!= route.points.size) {

            println("Size error.")
            assertTrue(false)
        }
        else {
            for(nrp in newRoute.points)
            {
                var flag2 =false
                for(rp in route.points)
                {
                    if(rp.equals(nrp)) {
                        flag2 = true
                        break
                    }
                }
                if(!flag2)
                {
                    println("Writane route not looks like reden route.")
                    assertTrue(false)
                }
            }
            println("Size test sucseed.")
            assertTrue(true)
        }


    }



}