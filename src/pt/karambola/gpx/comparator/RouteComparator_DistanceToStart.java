/*
 * RouteComparator_DistanceToStart.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;

public class
RouteComparator_DistanceToStart
	implements Comparator<Route>
{
	private final Point ref = new Point() ;

	public
	RouteComparator_DistanceToStart( final double refLat, final double refLon )
	{
		super( ) ;
		ref.setLatitude( refLat ) ;
		ref.setLongitude( refLon ) ;
	}

	public
	RouteComparator_DistanceToStart( final double refLat, final double refLon, final double refEle )
	{
		this( refLat, refLon ) ;
		ref.setElevation( refEle ) ;
	}


	@Override
	public int
	compare( final Route r1, final Route r2 )
	{
		if (r1 == r2)	return  0 ;
		if (r1 == null)	return -1 ;
		if (r2 == null) return  1 ;

		final double v1 = GpxUtils.distance( ref, r1.getRoutePoints( ).get( 0 ) ) ;
		final double v2 = GpxUtils.distance( ref, r2.getRoutePoints( ).get( 0 ) ) ;

		return Double.compare( v1, v2 ) ;
	}
}
