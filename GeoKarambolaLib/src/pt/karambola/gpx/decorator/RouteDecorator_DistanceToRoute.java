/*
 * RouteDecorator_DistanceToRoute.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.R3.R3;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Geo;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;

public class
RouteDecorator_DistanceToRoute
	implements StringDecorator<Route>
{
	private final R3 ref ;
	private final Units units ;

	public
	RouteDecorator_DistanceToRoute( double refLat, double refLon, final double refEle, Units units )
	{
		super( ) ;
		ref  = Geo.cartesian( refLat, refLon, refEle ) ;
		this.units  = units ;
	}

	public
	RouteDecorator_DistanceToRoute( double refLat, double refLon, Units units )
	{
		this( refLat, refLon, 0.0, units ) ;
	}


	@Override
	public
	String
	getStringDecoration( Route r )
	{
		final double[] distanceToRouteResults = GpxUtils.distanceToRoute( ref, r ) ;
		final String[] formatDistanceResults = Units.formatDistance( distanceToRouteResults[0], units ) ;

		return formatDistanceResults[0] + " " + formatDistanceResults[1] ;
	}
}
