/*
 * TrackDecorator_Length.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.util.GpxUtils;

public class
TrackDecorator_Length
implements StringDecorator<Track>
{
	private final Units units ;

	public
	TrackDecorator_Length( Units units )
	{
		super( ) ;
		this.units  = units ;
	}

	@Override
	public
	String
	getStringDecoration( Track trk )
	{
		String[] formatedDistance = Units.formatDistance( GpxUtils.lengthOfTrack( trk ), units ) ;

		return formatedDistance[0] + " " + formatedDistance[1] ;
	}
}
