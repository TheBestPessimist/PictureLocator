/*
 * TrackComparator_DistanceToPath.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.R3.R3;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.util.GpxUtils;

public class
TrackComparator_DistanceToPath
	implements Comparator<Track>
{
	private final R3  ref ;

	public
	TrackComparator_DistanceToPath( final double refLat, final double refLon, final double refEle )
	{
		super( ) ;
		ref = Geo.cartesian( refLat, refLon, refEle ) ;
	}

	public
	TrackComparator_DistanceToPath( final double refLat, final double refLon )
	{
		this( refLat, refLon, 0.0 ) ;
	}


	@Override
	public int
	compare( final Track t1, final Track t2 )
	{
		if (t1 == t2)	return  0 ;
		if (t1 == null)	return  1 ;
		if (t2 == null) return -1 ;

		final double v1 = GpxUtils.distanceToTrack( ref, t1 ) ;
		final double v2 = GpxUtils.distanceToTrack( ref, t2 ) ;

		return Double.compare( v1, v2 ) ;
	}
}
