/*
 * DefaultGpxBeanFactory.java
 * 
 * Copyright (c) 2016, Karambola. All rights reserved.
 */

package pt.karambola.gpx.parser;

import pt.karambola.gpx.beans.Gpx;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.beans.TrackPoint;
import pt.karambola.gpx.beans.TrackSegment;


public class
DefaultGpxBeanFactory
	implements GpxBeanFactory
{
	@Override
	public
	Gpx
	newGpx( )
	{
		return new Gpx( ) ;
	}

	@Override
	public
	Point
	newPoint( )
	{
		return new Point( ) ;
	}

	@Override
	public
	Route
	newRoute( )
	{
		return new Route( ) ;
	}

	@Override
	public
	RoutePoint
	newRoutePoint( )
	{
		return new RoutePoint( ) ;
	}

	@Override
	public
	Track
	newTrack( )
	{
		return new Track( ) ;
	}

	@Override
	public
	TrackSegment
	newTrackSegment( )
	{
		return new TrackSegment( ) ;
	}

	@Override
	public
	TrackPoint
	newTrackPoint( )
	{
		return new TrackPoint( ) ;
	}
}
