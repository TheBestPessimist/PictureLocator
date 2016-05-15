/*
 * TrackComparator_Length.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.util.GpxUtils;


public class
TrackComparator_Length
	implements Comparator<Track>
{
	public
	int
	compare( final Track t1, final Track t2 )
	{
		if (t1 == t2)	return  0 ;
		if (t1 == null)	return -1 ;
		if (t2 == null) return  1 ;

		final double v1 = GpxUtils.lengthOfTrack( t1 ) ;
		final double v2 = GpxUtils.lengthOfTrack( t2 ) ;

		return Double.compare( v1, v2 ) ;
	}
}
