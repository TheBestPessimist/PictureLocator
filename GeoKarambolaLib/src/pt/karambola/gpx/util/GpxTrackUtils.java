/*
 * GpxTrackUtils.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.gpx.util;

import java.util.ArrayList;
import java.util.List;

import pt.karambola.gpx.parser.GpxBeanFactory;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.beans.TrackPoint;
import pt.karambola.gpx.parser.DefaultGpxBeanFactory;

public class
GpxTrackUtils
{
    public static final GpxTrackUtils INSTANCE = new GpxTrackUtils( ) ;

    private final GpxBeanFactory beanFactory ;

    public
    GpxTrackUtils( final GpxBeanFactory beanFactory )
	{
		super( ) ;
		this.beanFactory = beanFactory ;
	}

    public
    GpxTrackUtils( )
	{
		this( new DefaultGpxBeanFactory( ) ) ;
	}


    /**
	 * One-to-one conversion of a track to a route. All track segments are joined, no track-point is discarded.
	 */
	public
	Route
	convertTrack( final Track track )
	{
		final Route route = beanFactory.newRoute( ) ;

		route.setName       ( track.getName()        ) ;
		route.setType       ( track.getType()        ) ;
		route.setDescription( track.getDescription() ) ;
		route.setComment    ( track.getComment()     ) ;
		route.setSrc        ( track.getSrc( ) != null ? track.getSrc( ) : GpxUtils.CREDITS ) ;

		final List<RoutePoint>	routePoints	= new ArrayList<>() ;

		for (TrackPoint trackPoint: track.getTrackPoints())
		{
			final RoutePoint routePoint = beanFactory.newRoutePoint( ) ;

			routePoint.setLatitude   ( trackPoint.getLatitude()    ) ;
			routePoint.setLongitude  ( trackPoint.getLongitude()   ) ;
			routePoint.setElevation  ( trackPoint.getElevation()   ) ;
			routePoint.setName       ( trackPoint.getName()        ) ;
			routePoint.setType       ( trackPoint.getType()        ) ;
			routePoint.setTime       ( trackPoint.getTime()        ) ;
			routePoint.setDescription( trackPoint.getDescription() ) ;
			routePoint.setComment    ( trackPoint.getComment()     ) ;

			routePoints.add( routePoint ) ;
		}

		route.setRoutePoints( routePoints ) ;

		return route ;
	}
}
