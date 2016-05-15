/*
 * RouteComparator_DistanceToPath.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.R3.R3;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;

public class
RouteComparator_DistanceToPath
	implements Comparator<Route>
{
	private final R3 ref ;

	public
	RouteComparator_DistanceToPath( final double refLat, final double refLon, final double refEle )
	{
		super( ) ;
		ref  = Geo.cartesian( refLat, refLon, refEle ) ;
	}

	public
	RouteComparator_DistanceToPath( double refLat, double refLon )
	{
		this( refLat, refLon, 0.0 ) ;
	}


	@Override
	public int
	compare( final Route r1, final Route r2 )
	{
		if (r1 == r2)	return  0 ;
		if (r1 == null)	return  1 ;
		if (r2 == null) return -1 ;

		final double[] distanceToRouteResults1 = GpxUtils.distanceToRoute( ref, r1 ) ;
		final double[] distanceToRouteResults2 = GpxUtils.distanceToRoute( ref, r2 ) ;

		final double distanceToR1 = distanceToRouteResults1[0] ;
		final double distanceToR2 = distanceToRouteResults2[0] ;

		return Double.compare( distanceToR1, distanceToR2 ) ;
	}
}
