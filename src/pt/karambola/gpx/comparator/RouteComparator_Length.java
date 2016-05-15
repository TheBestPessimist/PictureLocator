/*
 * RouteComparator_Length.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;


public class
RouteComparator_Length
	implements Comparator<Route>
{
	public
	int
	compare( final Route r1, final Route r2 )
	{
		if (r1 == r2)	return  0 ;
		if (r1 == null)	return -1 ;
		if (r2 == null) return  1 ;

		final double v1 = GpxUtils.lengthOfRoute( r1 ) ;
		final double v2 = GpxUtils.lengthOfRoute( r2 ) ;

		return Double.compare( v1, v2 ) ;
	}
}
