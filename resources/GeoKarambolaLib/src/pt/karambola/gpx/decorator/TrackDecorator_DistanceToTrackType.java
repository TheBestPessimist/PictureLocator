/*
 * TrackDecorator_DistanceToTrackType.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Track;

public class
TrackDecorator_DistanceToTrackType
	extends		TrackDecorator_DistanceToTrack
	implements StringDecorator<Track>
{
	public
	TrackDecorator_DistanceToTrackType( final double refLat, final double refLon, final double refEle, final Units units )
	{
		super( refLat, refLon, refEle, units ) ;
	}


	public
	TrackDecorator_DistanceToTrackType( final double refLat, final double refLon, final Units units )
	{
		super( refLat, refLon, units ) ;
	}


	@Override
	public
	String
	getStringDecoration( final Track t )
	{
		String result = super.getStringDecoration( t ) ;

		if (t.getType() != null)
			result += ", " + t.getType() ;

		return result ;
	}
}
