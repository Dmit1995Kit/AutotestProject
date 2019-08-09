package com.parcel.copter.tests_on_route

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(RouteNorthLatEastLon::class, RouteSounthLatWestLon::class,
                    RouteNorthLatWestLon::class, RouteNorthLatSouthLon::class,
                    RouteEastLatNorthLon::class, RouteEastLatSouthLon::class,
                    RouteEastLatWestLon::class,  RouteSouthLatNorthLon::class,
                    RouteEquatorPrimeMeridian::class, RouteNorthPole::class,
                    RouteSouthPole::class)
class RunGroupTests{

}