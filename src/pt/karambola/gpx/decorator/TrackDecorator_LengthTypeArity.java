/*
 * TrackDecorator_LengthTypeArity.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Track;


public class
TrackDecorator_LengthTypeArity
	extends TrackDecorator_Length
{
	public
	TrackDecorator_LengthTypeArity( Units units )
	{
		super( units ) ;
	}

	@Override
	public
	String
	getStringDecoration( Track trk )
	{
		String result = super.getStringDecoration( trk ) ;

		if (trk.getType() != null)
			result += ", " + trk.getType() ;

		result += ", #" + trk.getTrackPoints().size( ) ;

		return result ;
	}
}
