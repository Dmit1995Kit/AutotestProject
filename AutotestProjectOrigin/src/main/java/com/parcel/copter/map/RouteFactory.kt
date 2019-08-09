package com.parcel.copter.map

import com.google.gson.GsonBuilder
import com.parcel.copter.MapPoint
import com.peertopark.java.geocalc.DegreeCoordinate
import java.io.File
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths

object RouteFactory {

    class RouteFactoryExcepion(message: String) : Exception(message)

    //логгер
    private val logger = org.apache.log4j.Logger.getLogger(RouteFactory::class.java!!)

    class JsonPoint(val latitude : Double,val longitude:Double, val high: Int, val number: Int )

    class JsonRoute()
    {
        var points = ArrayList<JsonPoint>()
    }

    /********FILE********/
    fun getRoutFromFile(fileName: String):Route
    {
        logger.info("getRouteFromFile($fileName)")
        val file = File(fileName)
        if (file.exists()) {
            val text = file.readText()
            return getRouteFromJsonLine(text)
        }
        val errorLine = "File whis settings (filename = $fileName) not found."
        logger.error(errorLine)
        throw Exception(errorLine)
    }
    fun saveRouteToFile(fileName: String, route: Route)
    {
        logger.info("saveRouteToFile($fileName, route)")
        try {
            val file = File(fileName)
            val path = Paths.get(fileName)
            //проверяем наличие каталога, если катлона нет, создаем его
            if (!Files.exists(path)) {
                Files.createDirectories(path)
            }

            //проверяем, что если файл не существует то создаем его
            if (file.exists())
            {
                logger.warn("File $fileName is exist? it would be rewrite.")
                file.delete()
            }
            file.createNewFile()
            file.writeText(getJsonLineFromRoute(route))
        }
        catch (e: Exception)
        {
            val errorText = "Can` t save file $fileName. Error: $e"
            logger.error(errorText)
            throw RouteFactoryExcepion(errorText)
        }
    }

    /*******LINE*********/
    fun getRouteFromJsonLine(json: String) :Route
    {
        val jsonRouteoute = getJsonRouteFromJsonLine(json)
        jsonRouteoute.points.sortBy { it.number }
        val route = Route()
        jsonRouteoute.points.forEach{
            route.points.add(MapPoint(DegreeCoordinate(it.latitude), DegreeCoordinate(it.longitude), it.high, it.number))
        }
        return route
    }

    fun getJsonLineFromRoute(route: Route): String
    {
        val jsonRoute = JsonRoute()
        route.points.forEach{jsonRoute.points.add(JsonPoint(it.latitude, it.longitude, it.height, it.number))}
        return getJsonLineFromRoute(jsonRoute)
    }

    /******PRIVATE************/
    private fun getJsonRouteFromJsonLine(json: String) :JsonRoute
    {
        try {
            var builder = GsonBuilder()
            var gson = builder.create()
            return   gson.fromJson(json, JsonRoute().javaClass)
        }
        catch (e: Exception)
        {
            val errorLine = "Error JSON parsing."
            logger.error(errorLine)
            throw RouteFactoryExcepion(errorLine)

        }
    }

    private fun getJsonLineFromRoute(jsonRoute:JsonRoute): String
    {
        var builder =  GsonBuilder()
        var gson = builder.create()
        return gson.toJson(jsonRoute)
    }

}