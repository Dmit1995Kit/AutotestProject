import com.parcel.copter.MapPoint
import com.parcel.copter.map.Route
import com.parcel.copter.map.RouteFactory

fun main()
{
    createStandartRoute()
}

private fun createStandartRoute()
{
    val route = Route()
    route.points.add(MapPoint(55.688381, 37.770240, 0, 0))
    route.points.add(MapPoint(55.687803, 37.770523, 0, 1))
    route.points.add(MapPoint(55.687410, 37.770591, 0, 2))
    route.points.add(MapPoint(55.687289, 37.770678, 0, 3))
    RouteFactory.saveRouteToFile("Routes/testRoute.json", route)

}