/*
 * RouteDecorator_DistanceToStart.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;
import pt.karambola.gpx.util.GpxUtils;

public class
RouteDecorator_DistanceToStart
	implements StringDecorator<Route>
{
	private final Point ref = new Point() ;
	private final Units units ;

	public
	RouteDecorator_DistanceToStart( final double refLat, final double refLon, final double refEle, Units units )
	{
		this( refLat, refLon, units ) ;
		ref.setElevation( refEle ) ;
	}

	public
	RouteDecorator_DistanceToStart( final double refLat, final double refLon, Units units )
	{
		super( ) ;
		ref.setLatitude( refLat ) ;
		ref.setLongitude( refLon ) ;
		this.units  = units ;
	}


	@Override
	public
	String
	getStringDecoration( Route r )
	{
		final RoutePoint start = r.getRoutePoints( ).get( 0 ) ;
		final String[] formatedDistance = Units.formatDistance( GpxUtils.distance( ref, start ), units ) ;

		return formatedDistance[0] + " " + formatedDistance[1] ;
	}
}
