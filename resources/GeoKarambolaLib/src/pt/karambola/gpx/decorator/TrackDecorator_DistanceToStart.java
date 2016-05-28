/*
 * TrackDecorator_DistanceToStart.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.beans.TrackPoint;
import pt.karambola.gpx.util.GpxUtils;

public class
TrackDecorator_DistanceToStart
	implements StringDecorator<Track>
{
	private final Point ref = new Point() ;
	private final Units units ;

	public
	TrackDecorator_DistanceToStart( final double refLat, final double refLon, final double refEle, Units units )
	{
		this( refLat, refLon, units ) ;
		ref.setElevation( refEle ) ;
	}

	public
	TrackDecorator_DistanceToStart( final double refLat, final double refLon, Units units )
	{
		super( ) ;
		ref.setLatitude( refLat ) ;
		ref.setLongitude( refLon ) ;
		this.units  = units ;
	}


	@Override
	public
	String
	getStringDecoration( Track t )
	{
		TrackPoint start = t.getTrackSegments().get(0).getTrackPoints( ).get( 0 ) ;
		String[] formatedDistance = Units.formatDistance( GpxUtils.distance( ref, start ), units ) ;

		return formatedDistance[0] + " " + formatedDistance[1] ;
	}
}
