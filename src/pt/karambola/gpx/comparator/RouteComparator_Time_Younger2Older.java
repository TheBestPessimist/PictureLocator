/*
 * RouteComparator_Time_Younger2Older.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;

public class
RouteComparator_Time_Younger2Older
	implements Comparator<Route>
{
	public
	int
	compare( Route r1, Route r2 )
	{
		if (r1 == r2)	return  0 ;
		if (r1 == null)	return  1 ;
		if (r2 == null) return -1 ;

		RoutePoint start1 = r1.getRoutePoints().get(0) ;
		RoutePoint start2 = r2.getRoutePoints().get(0) ;

		return GenericPointComparator.TIME_YOUNGER2OLDER.compare( start1, start2 ) ;
	}
}
