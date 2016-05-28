/*
 * TrackDecorator_DistanceToTrack.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.R3.R3;
import pt.karambola.geo.Units;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.util.GpxUtils;

public class
TrackDecorator_DistanceToTrack
	implements StringDecorator<Track>
{
	private final R3 ref ;
	private final Units units ;

	public
	TrackDecorator_DistanceToTrack( final double refLat, final double refLon, final double refEle, final Units units )
	{
		super( ) ;
		ref = Geo.cartesian( refLat, refLon, refEle ) ;
		this.units  = units ;
	}

	public
	TrackDecorator_DistanceToTrack( final double refLat, final double refLon, final Units units )
	{
		this( refLat, refLon, 0.0, units ) ;
	}


	@Override
	public
	String
	getStringDecoration( final Track trk )
	{
		String[] formatedDistance = Units.formatDistance( GpxUtils.distanceToTrack( ref, trk ), units ) ;

		return formatedDistance[0] + " " + formatedDistance[1] ;
	}
}
